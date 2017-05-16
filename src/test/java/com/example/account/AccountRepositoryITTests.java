package com.example.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.example.account.Type.DUO;
import static com.example.account.Type.SOLO;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Az on 01/03/2017.
 * Useless comment
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryITTests {
    @Autowired
    AccountRepository accountRepository;

    Long currentSecondId;
    Long currentThirdId;

    @Before
    public void initialize_data(){
        AccountEntity accountEntityOne = AccountEntity.builder()
                .name("first")
                .type(SOLO).build();

        AccountEntity accountEntityTwo = AccountEntity.builder()
                .name("second")
                .type(DUO).build();

        AccountEntity accountEntityThree = AccountEntity.builder()
                .name("third")
                .type(DUO).build();

        accountRepository.save(accountEntityOne);
        accountRepository.save(accountEntityTwo);
        accountRepository.save(accountEntityThree);

        currentSecondId = accountEntityTwo.getId();
        currentThirdId = accountEntityThree.getId();
    }

    @After
    public void delete(){
        accountRepository.deleteAll();
    }

    @Test
    public void should_find_by_name(){
        String nameToSearch = "second";

        Optional<AccountEntity> accountEntity = accountRepository.findByName(nameToSearch);

        assertThat(accountEntity.isPresent());
        assertThat(accountEntity.get().getId()).isEqualTo(currentSecondId);
    }

    @Test
    public void should_find_by_name_native(){
        String nameToSearch = "second";

        AccountEntity accountEntity = accountRepository.nativeSearchByName(nameToSearch);

        assertThat(accountEntity.getId()).isEqualTo(currentSecondId);
    }

    @Test
    public void should_find_by_name_jpql(){
        String nameToSearch = "second";

        AccountEntity accountEntity = accountRepository.jpaSearchByName(nameToSearch);

        assertThat(accountEntity.getId()).isEqualTo(currentSecondId);
    }

    @Test
    public void should_find_by_type_paginated(){
        PageRequest pageRequest = new PageRequest(0, 1);

        Page<AccountEntity> accountEntityPage = accountRepository.findByType(DUO, pageRequest);

        assertThat(accountEntityPage.getTotalElements()).isEqualTo(2);
        assertThat(accountEntityPage.getTotalPages()).isEqualTo(2);
        assertThat(accountEntityPage.getContent()).hasSize(1);
        assertThat(accountEntityPage.getContent().get(0).getId()).isEqualTo(currentSecondId);
    }


    @Autowired
    TestEntityManager entityManager;

    @Test
    public void should_modify_name_by_id(){
        // Clear the context
        entityManager.clear();

        String newName = "second-changed";

        int affectedRows = accountRepository.updateName(newName, currentSecondId);
        AccountEntity accountEntity = accountRepository.findOne(currentSecondId);

        assertThat(affectedRows).isEqualTo(1);
        assertThat(accountEntity.getName()).isEqualTo(newName);
    }
}
