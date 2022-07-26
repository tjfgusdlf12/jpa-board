package jpaTestBoard.jpaboard.Dto.Session;

import jpaTestBoard.jpaboard.Dto.Common.Enum.Role;
import jpaTestBoard.jpaboard.Entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String nickName;
    private Role role;

    /*Entity --> Dto*/
    public UserSessionDto(Member member) {
        this.userId = member.getUserId();
        this.userName = member.getUserName();
        this.password = member.getPassword();
        this.nickName = member.getNickName();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
}
