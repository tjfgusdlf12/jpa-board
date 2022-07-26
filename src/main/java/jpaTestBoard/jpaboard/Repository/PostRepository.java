package jpaTestBoard.jpaboard.Repository;

import jpaTestBoard.jpaboard.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Posts, Long>,CustomizedPostRepository {
}
