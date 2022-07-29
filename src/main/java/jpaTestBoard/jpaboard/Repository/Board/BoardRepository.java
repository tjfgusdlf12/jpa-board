package jpaTestBoard.jpaboard.Repository.Board;


import com.querydsl.core.QueryResults;
import jpaTestBoard.jpaboard.Dto.Board.ReqBoard;
import jpaTestBoard.jpaboard.Entity.Board.Board;
import org.springframework.data.domain.Pageable;


public interface BoardRepository {
    QueryResults<Board> getBoardList(ReqBoard reqBoard, Pageable pageable);
}
