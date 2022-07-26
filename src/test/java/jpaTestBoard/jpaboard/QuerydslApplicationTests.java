package jpaTestBoard.jpaboard;

import jpaTestBoard.jpaboard.Repository.PostQueryRepository;
import jpaTestBoard.jpaboard.Repository.PostRepository;
import jpaTestBoard.jpaboard.Repository.PostRepositorySupport;
import jpaTestBoard.jpaboard.domain.Posts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@SpringBootTest
@Transactional
@Commit
public class QuerydslApplicationTests {

    @Autowired
    EntityManager em;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostRepositorySupport postRepositorySupport;

    @Autowired
    PostQueryRepository postQueryRepository;

/*    @Test
    public void findByTitle() {
        postRepository.saveAll(Arrays.asList(
                new Posts("test", "content","arpaka"),
                new Posts("test", "content","arpaka"),
                new Posts("test", "content","arpaka"),
                new Posts("title1", "content","Seol"),
                new Posts("title2", "content","Seol"),
                new Posts("title3", "content","Seol")
        ));

        final List<Posts> posts = postQueryRepository.findByTitle("test");

        assertAll(
                () -> assertThat(posts).hasSize(3),
                () -> assertThat(posts.get(0).getTitle()).isEqualTo("test")
        );
    }*/
}
