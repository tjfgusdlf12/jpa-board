package jpaTestBoard.jpaboard.Service;

import jpaTestBoard.jpaboard.Dto.PostRequestDto;
import jpaTestBoard.jpaboard.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;

    @Transactional              //메소드 실행시 트렌젝션 시작 - 정상 종료되면 커밋
    public Long save(PostRequestDto postRequestDto) {
        return postRepository.save(postRequestDto.toEntity()).getId();
    }
}
