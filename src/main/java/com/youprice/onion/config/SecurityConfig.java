package com.youprice.onion.config;

import com.youprice.onion.security.auth.CustomAuthFailureHandler;
import com.youprice.onion.security.auth.CustomAuthSuccessHandler;
import com.youprice.onion.service.member.impl.CustomUserDetailsService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthFailureHandler customFailureHandler;
    private final CustomAuthSuccessHandler customSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    //MemberApiController에서 사용할 AuthenticationManager Bean 등록
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //static 폴더의 하위 파일 목록은 인증 제외
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/template/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

		http.headers().frameOptions().sameOrigin(); // SockJS 동일한 사이트의 frame에서만 보여진다.

//        http.csrf().disable(); //csrf 비활성화 코드
        http.csrf().ignoringAntMatchers("/api/**"); //REST API 사용 예외처리(csrf 활성화 중이므로 jsp폼에서 POST로 데이터 넘겨주는 곳에서 hidden으로 csrf토큰 넘겨줘야 함)

        //페이지 권한 설정
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**/**.admin").hasRole("ADMIN")
                .antMatchers("/**/login", "/**/join", "/**/findId", "/**/findPwd").access("!hasRole('USER') and !hasRole('ADMIN')")
                .antMatchers("/member/mypage").authenticated()
                .antMatchers("/**").permitAll();

        //로그인 설정
        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("userId") //parameter명을 userId로 변경
                .passwordParameter("pwd") //parameter명을 pwd로 변경
                .loginProcessingUrl("/member/loginProc") //Security에서 해당 주소로 오는 요청을 낚아채서 수행
                .failureHandler(customFailureHandler) //로그인 실패 핸들러
                .defaultSuccessUrl("/") //로그인 성공 시 이동 페이지
                .successHandler(customSuccessHandler); //로그인 성공 핸들러

        //로그아웃 설정
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        //403 예외처리 핸들링
        http.exceptionHandling()
                .accessDeniedPage("/member/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
