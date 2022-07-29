package jpaTestBoard.jpaboard.Service.User;

import jpaTestBoard.jpaboard.Dto.Session.UserSessionDto;
import jpaTestBoard.jpaboard.Entity.Member;
import jpaTestBoard.jpaboard.Repository.UserRepo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));

        session.setAttribute("user", new UserSessionDto(member));
        return new CustomUserDetails(member);
    }
}
