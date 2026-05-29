package com.tanmoy.UserService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "micro_users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @Transient
    private List<Rating> ratingOfUser;
}
