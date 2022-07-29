package jpaTestBoard.jpaboard.Config;

import jpaTestBoard.jpaboard.Impl.MemberServiceImpl;
import jpaTestBoard.jpaboard.Service.User.CustomUserDetailsService;
import jpaTestBoard.jpaboard.Service.User.UserService;
import jpaTestBoard.jpaboard.Utils.TokenRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;
    private final TokenRequestFilter tokenRequestFilter;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/css/**", "/js/**", "/img/**");
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()    //토큰을 활용할 경우 모든 요청에 대해 접근이 가능하도록 함
                .antMatchers("/auth/**", "/posts/read/**", "/posts/search/**","api/v2/**","/swagger-resources/**","/swagger/**","/swagger-ui.html","/v2/api-docs","/board/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                *//*.loginPage("/auth/login")*//*
                .loginProcessingUrl("loginProc")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }*/

    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
                .antMatchers("/auth/**", "/posts/read/**", "/posts/search/**","api/v2/**","/swagger-resources/**","/swagger/**","/swagger-ui.html","/v2/api-docs","/board/**").permitAll()
                .anyRequest()
                .authenticated()
                .and() // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and() // form 기반의 로그인에 대해 비활성화 한다.
                .formLogin()
                .disable()
                .addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors();
    }

}
