package jpaTestBoard.jpaboard.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenRequestFilter extends OncePerRequestFilter {
    /**로그인 이후에 생성된 토큰은 화면에서 서버로 요청할 때 항상 존재해야함
     * 또한, 서버는 매번 받은 요청에서 토큰을 꺼내 확인해야 한다.
     *
     * 매번 컨트롤러에서 토큰을 확인하는 것은 반복적인 코드가 발생하므로 필터에서 작업하도록 할것임
     *
     * 그래서 스프링 필터 클래스를 사용하여 요청이 들어왔을때 앞단에서 처리가 가능.**/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if("/user/login".equals(request.getRequestURI())){
                doFilter(request, response, filterChain);
            } else {
                String token = parseJwt(request);
            }
        } catch (Exception e) {
            log.error("### Filter Exception {}",e.getMessage());
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
