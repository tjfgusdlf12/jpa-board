package jpaTestBoard.jpaboard.Controller;

import io.swagger.annotations.ApiOperation;
import jpaTestBoard.jpaboard.Dto.Common.CommonIdx;
import jpaTestBoard.jpaboard.Dto.Common.UserInfo;
import jpaTestBoard.jpaboard.Dto.ReqMember;
import jpaTestBoard.jpaboard.Service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "회원가입", tags = "팀/맴버")
    @PostMapping("/register/member")
    public Object registMember(HttpServletRequest req, @RequestBody UserInfo userInfo) {
        logger.info("[registMember] CONTROLLER -> registMember API Call");

        return  memberService.joinMember(userInfo).getResponse();
    }

    @ApiOperation(value = "팀 맴버 조회", tags = "팀/맴버")
    @PostMapping("/teamMember")
    public Object getTeamMemberList(HttpServletRequest req, @RequestBody CommonIdx commonIdx) {
        logger.info("[CONTROLLER] CONTROLLER -> getTeamMemberList API Call");

        return memberService.findMySymReviewIdxList(commonIdx).getResponse();
    }

    @ApiOperation(value="팀 맴버 등록", tags = "팀/맴버")
    @PostMapping("/resistMember")
    public Object resistMember(HttpServletRequest req, @RequestBody ReqMember reqMember) {
        logger.info("[CONTROLLER] CONTROLLER -> resistMember");
        return memberService.resistMember(reqMember).getResponse();
    }
}
