package jpaTestBoard.jpaboard.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpaTestBoard.jpaboard.domain.Posts;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jpaTestBoard.jpaboard.domain.QPosts.posts;

@Repository
public class PostQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public PostQueryRepository(final JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Posts> findByTitle(final String title){
        return jpaQueryFactory.selectFrom(posts)
                .where(posts.title.eq(title))
                .fetch();
    }
}
