package com.example.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Az on 02/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountRepositoryJdbc.class)
@DataJpaTest
@AccountData
public class AccountRepositoryJdbcIT {
    @Autowired
    AccountRepositoryJdbc accountRepositoryJdbc;

    @Test
    public void should_find_by_name(){
        String searchName = "second";

        assertThat(accountRepositoryJdbc.findByName(searchName).getName()).isEqualTo(searchName);
        assertThat(accountRepositoryJdbc.findByName(searchName).getId()).isEqualTo(2);
    }
}
