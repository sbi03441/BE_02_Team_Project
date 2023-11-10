package com.b2.supercoding_prj01.repository.comments;

import com.b2.supercoding_prj01.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {
}
