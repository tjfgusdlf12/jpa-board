/*package jpaTestBoard.jpaboard.domain.posts;

import jpaTestBoard.jpaboard.Repository.PostRepository;
import jpaTestBoard.jpaboard.domain.Posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)                //스프링부트와 제이유닛 사이를 연결
public class PostsRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After                                  //단위 테스트 끝날때마다 실행될 메서드
    public void cleanUp(){
        postRepository.deleteAll();         //테스트 끝나면 그 내용도 사라짐
    }

    @Test
    public void 게시글등록_불러오기테스트(){

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("홍길동")
                .build());

        //when
        List<Posts> postsList = postRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}*/
