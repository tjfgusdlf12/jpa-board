package jpaTestBoard.jpaboard.Controller;

import jpaTestBoard.jpaboard.Dto.PostRequestDto;
import jpaTestBoard.jpaboard.Service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/saveForm/post")
    public Long save(@RequestBody PostRequestDto postRequestDto){
        return postsService.save(postRequestDto);
    }
}
