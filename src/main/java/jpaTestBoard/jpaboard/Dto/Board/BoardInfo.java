package jpaTestBoard.jpaboard.Dto.Board;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardInfo {

    @ApiModelProperty(value = "게시글 idx")
    private Long idx;

    @ApiModelProperty(value = "게시글 내용")
    private String content;

    @ApiModelProperty(value = "게시글 제목")
    private String title;

    @ApiModelProperty(value = "작성자")
    private String author;

    @ApiModelProperty(value = "조회수")
    private Integer viewCnt;

    @ApiModelProperty(value = "등록일시")
    private String  regDt;
}
