package com.b2.supercoding_prj01.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 200) //인코딩된 비밀번호 길이에 대비하여 넉넉하게 설정
    private String password;

}
