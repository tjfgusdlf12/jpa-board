package jpaTestBoard.jpaboard.Service.Board;

import com.querydsl.core.QueryResults;
import jpaTestBoard.jpaboard.Common.Utils.DateUtil;
import jpaTestBoard.jpaboard.Common.Utils.RestResult;
import jpaTestBoard.jpaboard.Dto.Board.BoardInfo;
import jpaTestBoard.jpaboard.Dto.Board.ReqBoard;
import jpaTestBoard.jpaboard.Dto.Common.UserInfo;
import jpaTestBoard.jpaboard.Entity.Board.Board;
import jpaTestBoard.jpaboard.Repository.Board.BoardRepo;
import jpaTestBoard.jpaboard.Repository.Board.BoardRepository;
import jpaTestBoard.jpaboard.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.AsyncContext;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final BoardRepository boardRepository;
    private final BoardRepo boardRepo;

    public RestResult getBoardList(ReqBoard reqBoard) {
        RestResult result = RestResult.getDefResult();

        try {
            QueryResults<Board> boardList = boardRepository.getBoardList(reqBoard);

            result.addObject("data",boardList.getResults());
            result.addObject("total",boardList.getTotal());

        } catch (Exception e) {
            logger.error("",e);
            result.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    public RestResult getBoardDetail(Long idx) {
        RestResult result = RestResult.getDefResult();

        try {
            Board boardDetail = boardRepo.getByIdx(idx);

            result.addObject("data", boardDetail);
        } catch (Exception e) {
            logger.error("",e);
            result.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    public RestResult regupdateBoard(BoardInfo boardInfo) {
        RestResult result = RestResult.getDefResult();

        try {
            Timestamp now = new Timestamp(new Date().getTime());
            Optional<Board> findDetail = boardRepo.findById(boardInfo.getIdx());
            Board board = Board.builder()
                    .title(boardInfo.getTitle())
                    .content(boardInfo.getContent())
                    .regDt(now)
                    .viewCnt(0)
                    .author(boardInfo.getAuthor())
                    .build();

            boardRepo.save(board);
        } catch(Exception e) {
            logger.error("",e);
            result.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    public RestResult deleteBoard(Long idx) {
        RestResult result = RestResult.getDefResult();
        try {
            Board findDetail = boardRepo.getByIdx(idx);
            boardRepo.delete(findDetail);
        } catch (Exception e) {
            logger.error("",e);
            result.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}
