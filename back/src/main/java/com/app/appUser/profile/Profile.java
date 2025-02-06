package com.app.appUser.profile;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Profile implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "profile_g")
    @SequenceGenerator(name = "profile_g", sequenceName = "profile_seq", allocationSize = 1)
    private Long id;

    private String fio = "ФИО";
    private String post = "Должность";
    private String email = "Почта";
    private String tel = "Телефон";
    private String org = "Организация";

    public Profile(String fio, String post, String email, String tel, String org) {
        this.fio = fio;
        this.post = post;
        this.email = email;
        this.tel = tel;
        this.org = org;
    }

    public void update(Profile update) {
        this.fio = update.getFio();
        this.email = update.getEmail();
        this.tel = update.getTel();
        this.org = update.getOrg();
    }
}