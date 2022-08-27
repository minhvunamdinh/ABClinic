package com.medical.examination.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.medical.examination.service.impl.AccountDetailsServiceImpl;
import com.medical.examination.utils.AuthTokenFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AccountDetailsServiceImpl accountDetailsServiceImpl;
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("Minhvu@fpt")).roles("ADMIN");
		authenticationManagerBuilder.userDetailsService(accountDetailsServiceImpl).passwordEncoder(passwordEncoder()); //cung cấp service cho spring security;
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable()
//		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//		.authorizeRequests()
//		.antMatchers("*").permitAll()
//		.antMatchers("/api/auth/**").permitAll()
//		.antMatchers("/api/**").permitAll()
//		.and()
//		.formLogin() // Cho phép người dùng xác thực bằng form login
//		.loginPage("/login") // Sử dụng custom form login
//		.defaultSuccessUrl("/home", true)
//		.permitAll() // Tất cả đều được truy cập vào địa chỉ này
//		.and()
//		.logout() // Cho phép logout
//		.permitAll();
		
		http.authorizeRequests()
		.antMatchers("/assets/**").permitAll()
		.antMatchers("/contactform/**").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/fonts/**").permitAll()
		.antMatchers("/img/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/login").permitAll()
		.anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
		.and()
		.formLogin() // Cho phép người dùng xác thực bằng form login
		.loginPage("/login") // Sử dụng custom form login
		.defaultSuccessUrl("/home", true)
		.permitAll() // Tất cả đều được truy cập vào địa chỉ này
		.and()
		.logout() // Cho phép logout
		.permitAll()
		.and()
		.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
//		http.cors().and().csrf().disable()
//		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//		.authorizeRequests().antMatchers("/api/auth/**").permitAll()
//		.antMatchers("*").permitAll();
//		.anyRequest().permitAll();
	}
	
	RequestMatcher csrfRequestMatcher = new RequestMatcher() {

    	private Pattern allowedMethods = Pattern.compile("^GET$");

        @Override
        public boolean matches(HttpServletRequest request) {
            if (allowedMethods.matcher(request.getMethod()).matches()) {
                return false;
            }
            return true;
        }

      }; // new RequestMatcher
    
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
      }
}
