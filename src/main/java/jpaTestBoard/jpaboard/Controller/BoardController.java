package jpaTestBoard.jpaboard.Controller;

import io.swagger.annotations.ApiOperation;
import jpaTestBoard.jpaboard.Dto.Board.BoardInfo;
import jpaTestBoard.jpaboard.Dto.Board.ReqBoard;
import jpaTestBoard.jpaboard.Dto.Common.UserInfo;
import jpaTestBoard.jpaboard.Service.Board.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final BoardService boardService;

    @ApiOperation(value = "게시글 호출", tags = "Board")
    @PostMapping("/board/list")
    public Object boardList(HttpServletRequest req, @RequestBody ReqBoard reqBoard) {
        logger.info("[boardList] CONTROLLER -> boardList API Call");

        return  boardService.getBoardList(reqBoard).getResponse();
    }

    @ApiOperation(value = "게시글 상세 호출", tags = "Board")
    @PostMapping("/board/detail/{idx}")
    public Object boardDetail(HttpServletRequest req, @PathVariable("idx") Long idx) {
        logger.info("[boardList] CONTROLLER -> boardList API Call");

        return  boardService.getBoardDetail(idx).getResponse();
    }

    @ApiOperation(value = "게시글 등록", tags = "Board")
    @PostMapping("/board/regist")
    public Object regiUpdateBoard(HttpServletRequest req, @RequestBody BoardInfo boardInfo) {
        logger.info("[regiUpdateBoard] CONTROLLER -> registBoard API Call");

        return  boardService.regupdateBoard(boardInfo).getResponse();
    }

    @ApiOperation(value = "게시글 삭제", tags = "Board")
    @PostMapping("/board/delete/{idx}")
    public Object deleteBoard(HttpServletRequest req, @PathVariable("idx") Long idx) {
        logger.info("[deleteBoard] CONTROLLER -> registBoard API Call");

        return  boardService.deleteBoard(idx).getResponse();
    }
}
