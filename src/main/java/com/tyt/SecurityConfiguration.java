package com.tyt;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().configurationSource(corsConfigurationSource()).and()				
				.rememberMe().disable()
				.authorizeHttpRequests((authorize) -> authorize
						.anyRequest().authenticated()
				)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.httpBasic(withDefaults())
				.formLogin(withDefaults())				
				.csrf().disable();
	}
	    
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type",  "x-auth-token", "cache-control"));
        configuration.setExposedHeaders(Arrays.asList("authorization", "x-auth-token"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        //source.registerCorsConfiguration("/api/trails/**", configuration);
        //FilterRegistrationBean<Filter> bean = new FilterRegistrationBean(new CorsFilter(source));
        //bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return source;
    }
	 
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
