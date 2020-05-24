package ir.hamyiar.hamyshow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailsService applicationUserDetailsService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     ApplicationUserDetailsService applicationUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserDetailsService = applicationUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/about", "/register","/movies/**", "/resources/**").permitAll()
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


/*    @Bean
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
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserDetailsService);
        return provider;
    }

}
