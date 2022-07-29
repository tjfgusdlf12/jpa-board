package jpaTestBoard.jpaboard.Repository.Board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpaTestBoard.jpaboard.Dto.Board.ReqBoard;
import jpaTestBoard.jpaboard.Entity.Board.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static jpaTestBoard.jpaboard.Entity.Board.QBoard.board;


@Repository
public class CustomBoardRepo implements BoardRepository{

    private final JPAQueryFactory query;
    public CustomBoardRepo(JPAQueryFactory jpaQueryFactory) {
        this.query = jpaQueryFactory;
    }

    @Override
    public QueryResults<Board> getBoardList(ReqBoard reqBoard, Pageable pageable) {
        return query.selectFrom(board)
                .where(BoardWhere(reqBoard))
                .orderBy(board.idx.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    private BooleanBuilder BoardWhere(ReqBoard data) {
        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isEmpty(data.getSrchKeyword()))return null;
        if(data.getSrchType() == 1){
            builder.and(board.title.contains(data.getSrchKeyword()));
        } else if(data.getSrchType() == 2){
            builder.and(board.content.contains(data.getSrchKeyword()));
        } else if(data.getSrchType() == 3){
            builder.and(board.author.contains(data.getSrchKeyword()));
        }
        return builder;
    }
}
