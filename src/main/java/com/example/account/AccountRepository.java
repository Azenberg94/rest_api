package com.example.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Az on 01/03/2017.
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>{

    @Query("update AccountEntity set name = :new_name where id = :id")
    @Modifying
    int updateName(@Param("new_name") String name, @Param("id") Long id);

    Optional<AccountEntity> findByName(String nameToSearch);

    @Query("select ae from AccountEntity ae where ae.name = :search_name")
    AccountEntity jpaSearchByName(@Param("search_name") String name);

    @Query(nativeQuery = true,
            value = "select ae.* from account ae where ae.name = :search_name"
    )
    AccountEntity nativeSearchByName(@Param("search_name") String name);

    Page<AccountEntity> findByType(Type type, Pageable pageable);
}
