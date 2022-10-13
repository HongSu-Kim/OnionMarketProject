package com.youprice.onion.config;

import com.youprice.onion.service.member.impl.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //static 폴더의 하위 파일 목록은 인증 제외
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/template/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf 비활성화(나중에 활성화?)
        //페이지 권한 설정
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/member/myinfo").authenticated()
                .antMatchers("/**").permitAll();

        //로그인 설정
        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("userId") //parameter명을 userId로 변경
                .passwordParameter("pwd") //parameter명을 pwd로 변경
                .loginProcessingUrl("/member/loginProc") //Security에서 해당 주소로 오는 요청을 낚아채서 수행
                .defaultSuccessUrl("/member/home") //로그인 성공 시 이동 페이지
                .permitAll();

        //로그아웃 설정
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/home")
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
