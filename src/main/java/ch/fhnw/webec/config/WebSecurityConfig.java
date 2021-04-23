package ch.fhnw.webec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/css/stylesheet.css").permitAll()
                .antMatchers(HttpMethod.GET, "/", "/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/", "/js/**").authenticated()
                .antMatchers(HttpMethod.GET, "/add").authenticated()
                .antMatchers(HttpMethod.GET, "/rating").authenticated()
                .antMatchers(HttpMethod.POST, "/").authenticated()
                .antMatchers(HttpMethod.POST, "/rating").authenticated()
                .antMatchers(HttpMethod.DELETE, "/places/**").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login").loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true).permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout").permitAll();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        final User.UserBuilder builder = User.withDefaultPasswordEncoder();
        final UserDetails user = builder.username("user").password("user").roles("USER").build();
        final UserDetails admin = builder.username("admin").password("admin").roles("ADMIN", "USER").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
