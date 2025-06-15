package org.sopt.post.core.repository;

import org.sopt.post.core.domain.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByUserIdAndId(Long userId, Long postId);
    List<PostEntity> findAll();

    @Query("""
        SELECT p
        FROM PostEntity p
        WHERE p.title LIKE CONCAT('%', :keyword, '%')
    """)
    List<PostEntity> findByKeyword(String keyword);

    @Query("""
    SELECT p
    FROM PostEntity p
    ORDER BY p.createdAt DESC
""")
    List<PostEntity> findAllOrderByCreatedAtDesc();
}