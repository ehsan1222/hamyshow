package ir.hamyiar.hamyshow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static ir.hamyiar.hamyshow.security.auth.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/about", "/movies/**", "/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/accessdenied");
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails ehsan = User.builder()
                .username("ehsan")
                .passwordEncoder(passwordEncoder::encode)
                .password("password")
                .authorities(USER.getGrantedAuthority())
                .build();

        UserDetails zahra = User.builder()
                .username("zahra")
                .passwordEncoder(passwordEncoder::encode)
                .password("zahraie")
                .authorities(PREMIUM.getGrantedAuthority())
                .build();

        UserDetails omid = User.builder()
                .username("omid")
                .password("omidi")
                .passwordEncoder(passwordEncoder::encode)
                .authorities(ADMIN.getGrantedAuthority())
                .build();

        return new InMemoryUserDetailsManager(
                ehsan, zahra, omid
        );
    }


}
