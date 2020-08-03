package com.example.authorbook.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="author")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String username;
    @Column
    private String password;
    @Transient
    private String confirmPassword;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('MALE','FEMALE')")
    private Gender gender;
    @Column
    private String bio;
    @Column(name = "profile_pic")
    private String profilePic;
    @Column
    private boolean active;
    @Column
    private String token;




}
