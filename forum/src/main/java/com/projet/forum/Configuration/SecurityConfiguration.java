package com.projet.forum.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projet.forum.Configuration.Filters.JwtFilter;

import jakarta.servlet.Filter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private final JwtFilter filter;

    public SecurityConfiguration(JwtFilter filter) {
        this.filter = filter;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    //hashing with BCrypt algo
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

   /*  @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder){

        UserDetails admin = User.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("test"))
                                .roles("ADMIN", "MODERATOR", "USER")
                                .build();

        UserDetails moderator = User.builder()
                                .username("moderator")
                                .password(passwordEncoder.encode("test"))
                                .roles("MODERATOR", "USER")
                                .build();

        UserDetails user = User.builder()
                                .username("user")
                                .password(passwordEncoder.encode("test"))
                                .roles("USER")
                                .build();

        return new InMemoryUserDetailsManager(admin, moderator, user);
    }*/

    //authorize pages for logged users
    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.cors().and().csrf().disable()
             .authorizeHttpRequests(registry -> {
                registry.requestMatchers("/channels/*").permitAll()
                .requestMatchers("/channels").permitAll()
                //.requestMatchers("/login").permitAll()
                .requestMatchers("/sign_up/*").permitAll()
                .requestMatchers("/users_info/*").permitAll()
                .requestMatchers("/post").permitAll()
                .requestMatchers("/post/getName").permitAll()
                .requestMatchers("/login").anonymous()
                .requestMatchers("/testAuth").authenticated()
                .requestMatchers("/posts/createPost").authenticated()
                        .requestMatchers("/admin/**").authenticated();
                        try{
                            registry.anyRequest().authenticated().and().sessionManagement(
                                sm -> sm.sessionCreationPolicy(
                                    SessionCreationPolicy.STATELESS
                                )
                            );
                        } catch (Exception e) { throw new RuntimeException(e); }
            });

       /* http.authorizeHttpRequests(registry -> {
            registry.requestMatchers("/message/*").authenticated()
                    .requestMatchers("/channels/*").anonymous()
                    .requestMatchers("posts/**").anonymous()
                    .requestMatchers("/login/admin").authenticated()
                    .requestMatchers("/login").anonymous()
                    .requestMatchers("/**").permitAll();
        });
        */
        return http.build();
    }

}
