package jpaTestBoard.jpaboard.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(nullable = false, name = "user_id")
    private String userId;

    @Column(nullable = false, name = "user_pw")
    private String userPw;

    @Column(nullable = false, name = "user_email")
    private String userEmail;

    @Column(nullable = false, name = "user_gender")
    private String userGender;

    @Column(nullable = false, name = "user_phone")
    private String userPhone;

    @Column(nullable = false, name = "user_name")
    private String userName;

}
