package jpaTestBoard.jpaboard.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jpaTestBoard.jpaboard.Dto.Common.Enum.Role;
import jpaTestBoard.jpaboard.Dto.ReqMember;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long idx;

    @Column(nullable = false, name = "user_id")
    private String userId;

    @Column(nullable = false, name = "userName")
    private String  userName;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, length = 400, name = "password")
    private String password;

    @Column(nullable = false, length = 400, name = "nick_name")
    private String nickName;

    @Column(nullable = false, name = "gender")
    private String gender;

    @Column(nullable = false,name = "age")
    private Integer age;

    @Column(nullable = false, name = "join_regDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp joinRegDt;

    @Column(nullable = false, name = "user_phone")
    private String userPhone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public static Member insertMember(ReqMember reqMember, Team team) {
        return Member.builder()
                .userName(reqMember.getUserName())
                .team(team)
                .build();
    }

    public static Member insertMember2(ReqMember reqMember) {
        return Member.builder()
                .userName(reqMember.getUserName())
                .team(Team.builder().name(reqMember.getTeamName()).build())
                .build();
    }
}
