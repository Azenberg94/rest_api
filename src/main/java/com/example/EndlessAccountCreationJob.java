package com.example;

import com.example.account.AccountEntity;
import com.example.account.AccountRepository;
import com.example.account.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by Az on 03/03/2017.
 */
@Component
public class EndlessAccountCreationJob {

    public static Logger logger = LoggerFactory.getLogger(AccountEntity.class);
    private AccountRepository accountRepository;
    private Long counter = 0L;

    @Autowired
    public EndlessAccountCreationJob(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Scheduled(fixedDelay = 1000)
    public void createRandomAccount(){
        Type type = (counter % 2 == 0) ? Type.SOLO : Type.DUO;

        String name = "Random" + ++counter;

        AccountEntity accountEntity = AccountEntity.builder()
                .name(name)
                .type(type)
                .build();

        accountRepository.save(accountEntity);
        logger.info("{} created", accountEntity);
    }
}
