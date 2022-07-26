package jpaTestBoard.jpaboard.Dto;

import jpaTestBoard.jpaboard.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostRequestDto {
    /*거의 엔티티 클래스와 비슷하지만 엔티티 클레스로는 데이터를 주고받을 수 없다잉*/
    private Long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostRequestDto(String title, String content, String author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //Dto에서 필요한 부분을 entity화 시킴
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
