package com.b2.supercoding_prj01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_idx")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    private String email;
    private String title;
    private String author;

    @Column(name = "created_at")
    private Timestamp createdAt;

    private String content;
}
