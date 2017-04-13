package com.example.account;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

/**
 * Created by Az on 01/03/2017.
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    @Enumerated(STRING)
    private Type type;
}