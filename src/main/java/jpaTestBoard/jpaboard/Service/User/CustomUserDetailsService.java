package jpaTestBoard.jpaboard.Service.User;

import jpaTestBoard.jpaboard.Dto.Session.UserSessionDto;
import jpaTestBoard.jpaboard.Entity.Member;
import jpaTestBoard.jpaboard.Repository.UserRepo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.management.relation.Relation;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));

        session.setAttribute("user", new UserSessionDto(member));
        return new CustomUserDetails(member);
    }
}
