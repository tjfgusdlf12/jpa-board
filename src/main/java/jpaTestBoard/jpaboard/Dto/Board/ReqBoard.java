package jpaTestBoard.jpaboard.Dto.Board;

import jpaTestBoard.jpaboard.model.Pagination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqBoard extends Pagination {

    private String srchKeyword;

    private Integer srchType;

}
