package com.b2.supercoding_prj01.repository.user;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userId;

    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

}
