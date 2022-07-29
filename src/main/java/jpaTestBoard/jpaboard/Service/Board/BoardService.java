package jpaTestBoard.jpaboard.Service.Board;

import com.querydsl.core.QueryResults;
import jpaTestBoard.jpaboard.Common.Utils.RestResult;
import jpaTestBoard.jpaboard.Dto.Board.BoardInfo;
import jpaTestBoard.jpaboard.Dto.Board.ReqBoard;
import jpaTestBoard.jpaboard.Entity.Board.Board;
import jpaTestBoard.jpaboard.Repository.Board.BoardRepo;
import jpaTestBoard.jpaboard.Repository.Board.BoardRepository;
import jpaTestBoard.jpaboard.Service.MemberService;
import jpaTestBoard.jpaboard.model.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final BoardRepository boardRepository;
    private final BoardRepo boardRepo;

    public RestResult getBoardList(ReqBoard reqBoard, Pageable pageable) {
        RestResult result = RestResult.getDefResult();
        List<BoardInfo> resultList = new ArrayList<>();
        try {
            QueryResults<Board> boardList = boardRepository.getBoardList(reqBoard,pageable);

            for(Board el : boardList.getResults()){

                BoardInfo info = BoardInfo.builder()
                        .idx(el.getIdx())
                        .title(el.getTitle())
                        .content(el.getContent())
                        .author(el.getAuthor())
                        .viewCnt(el.getViewCnt())
                        .regDt(String.valueOf(el.getRegDt()))
                        .build();
                resultList.add(info);
            }

            Pagination pagination = new Pagination(
                    (int) boardList.getTotal()
                    ,pageable.getPageNumber() + 1
                    ,pageable.getPageSize()
                    ,10
            );

            result.addObject("data",boardList.getResults());
            result.addObject("pagination", pagination);
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
            Optional<Board> findDetail = boardRepo.findByIdx(boardInfo.getIdx());
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
