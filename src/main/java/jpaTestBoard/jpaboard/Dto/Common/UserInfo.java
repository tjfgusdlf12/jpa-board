package jpaTestBoard.jpaboard.Dto.Common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class UserInfo {

    @ApiModelProperty(value = "유저 아이디")
    private String userId;

    @ApiModelProperty(value = "유저 페스워드")
    private String password;

    @ApiModelProperty(value = "유저 name")
    private String  userName;

    @ApiModelProperty(value = "유저 이메일")
    private String email;

    @ApiModelProperty(value = "유저 닉네임")
    private String nickName;

    @ApiModelProperty(value = "유저 성별")
    private String gender;

    @ApiModelProperty(value = "유저 나이")
    private Integer age;

    @ApiModelProperty(value = "유저 전화번호")
    private String userPhone;

    @ApiModelProperty(value = "가입 날짜")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp joinRegDt;
}
