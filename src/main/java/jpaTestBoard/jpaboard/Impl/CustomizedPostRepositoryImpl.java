package jpaTestBoard.jpaboard.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpaTestBoard.jpaboard.Repository.CustomizedPostRepository;
import jpaTestBoard.jpaboard.domain.Posts;

import java.util.List;

import static jpaTestBoard.jpaboard.domain.QPosts.posts;

public class CustomizedPostRepositoryImpl implements CustomizedPostRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private CustomizedPostRepositoryImpl(final JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Posts> findByTitle(final String title) {
        return jpaQueryFactory.selectFrom(posts)
                .where(posts.title.eq(title))
                .fetch();
    }
}
