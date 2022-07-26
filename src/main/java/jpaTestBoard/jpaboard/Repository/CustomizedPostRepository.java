package jpaTestBoard.jpaboard.Repository;

import jpaTestBoard.jpaboard.domain.Posts;

import java.util.List;

public interface CustomizedPostRepository {
    List <Posts> findByTitle(final String title);
}
