package jpaTestBoard.jpaboard.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpaTestBoard.jpaboard.domain.Posts;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jpaTestBoard.jpaboard.domain.QPosts.posts;

@Repository
public class PostRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositorySupport(final JPAQueryFactory jpaQueryFactory) {
        super(Posts.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Posts> findByTitle(final String title){
        return jpaQueryFactory.selectFrom(posts)
                .where(posts.title.eq(title))
                .fetch();
    }
}
