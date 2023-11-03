package com.b2.supercoding_prj01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "comments")
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_idx")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "board_idx")
    private BoardEntity boardEntity;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    private String content;
    private String author;

    @Column(name = "created_at")
    private Timestamp createdAt;
}
