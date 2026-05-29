package com.tanmoy.UserService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "micer_users")
@Setter
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "about")
    private String about;
}
