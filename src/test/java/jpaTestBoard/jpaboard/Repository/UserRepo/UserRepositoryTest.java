package jpaTestBoard.jpaboard.Repository.UserRepo;

import jpaTestBoard.jpaboard.Entity.User.UserEntity;
import jpaTestBoard.jpaboard.Service.User.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

/*    @DisplayName("1. 유저 데이터 생성하기")
    @Test
    void test_1(){
        String entPassword = passwordEncoder.encode("tjf19091406");
        UserEntity userEntity = UserEntity.builder()
                .userId("tjfgusdlf12")
                .userPw(entPassword)
                .userEmail("tjfgusdlf12@naver.com")
                .userPhone("010-7138-6816")
                .userName("설현일")
                .userGender("M")
                .build();

        UserEntity savedUser = userRepository.save(userEntity);
        assertThat(userEntity.getUserId()).isEqualTo(savedUser.getUserId());
    }*/

    @DisplayName("2. 유저정보 검색 후 비밀번호 비교")
    @Test
    void test_2(){
        /*String encPassword = passwordEncoder.encode("test_password");

        UserEntity user =  userRepository.findByUserId("tjfgusdlf12")
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        assertThat(user.getUserPw()).isEqualTo(encPassword);*/

        /**성공 예상했으나 비번이 맞지 않아 실패
         * Why?
         * PasswordEncorder를 통해 비번을 암호화 하여 단순하게 비교하는 방식은 허용하지 않기 때문**/

        String userId = "tjfgusdlf12";
        String userPw = "tjf19091406";
        UserDetails user = userService.loadUserByUsername(userId);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, userPw);
        authenticationManager.authenticate(authenticationToken);

        assertThat(authenticationToken.getCredentials()).isEqualTo(userPw);

        System.out.println("getCredentials: " + authenticationToken.getCredentials());
        System.out.println("userPw: " + userPw);

    }
}
