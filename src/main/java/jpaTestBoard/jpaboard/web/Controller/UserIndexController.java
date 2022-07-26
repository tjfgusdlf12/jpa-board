package jpaTestBoard.jpaboard.web.Controller;

import jpaTestBoard.jpaboard.Dto.Common.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserIndexController {

/*    @GetMapping("/")
    public String home(){
        return "home";
    }*/

    @PostMapping("/auth/join")
    public String join() {
        return "/user/user-join";
    }

    @PostMapping("/auth/joinProc")
    public String joinProc(UserInfo userInfo) {

        return null;
    }

    @PostMapping("/auth/login")
    public String login() {

        return "/user/user-login";
    }
}
