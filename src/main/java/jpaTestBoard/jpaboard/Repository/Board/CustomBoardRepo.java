package jpaTestBoard.jpaboard.Repository.Board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpaTestBoard.jpaboard.Dto.Board.ReqBoard;
import jpaTestBoard.jpaboard.Entity.Board.Board;
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
    public QueryResults<Board> getBoardList(ReqBoard reqBoard) {
        return query.selectFrom(board)
                .where(BoardWhere(reqBoard))
                .orderBy(board.idx.desc())
                .fetchResults();
    }

    private BooleanBuilder BoardWhere(ReqBoard data) {
        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isEmpty(data.getKeyWords()))return null;
        if(data.getOption() == 1){
            builder.and(board.title.contains(data.getKeyWords()));
        } else if(data.getOption() == 2){
            builder.and(board.content.contains(data.getKeyWords()));
        } else if(data.getOption() == 3){
            builder.and(board.author.contains(data.getKeyWords()));
        }
        return builder;
    }
}
