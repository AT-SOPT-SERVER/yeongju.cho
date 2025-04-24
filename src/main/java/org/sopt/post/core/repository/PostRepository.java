package org.sopt.post.core.repository;

import org.sopt.post.core.domain.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    Optional<PostEntity> findById(final int diaryId);
    List<PostEntity> findAll();
}