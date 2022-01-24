package com.teamfaceless.facelessjobs.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email_credencial, pass_credencial, activo_credencial from credencial where email_credencial=?")
                .authoritiesByUsernameQuery("SELECT email_credencial, nombre_rol FROM ((rol INNER JOIN credencial_roles " +
                " ON rol.id_rol = roles_id_rol) INNER JOIN credencial ON credencial.id_credencial = credencial_id_credencial) " +
                " where email_credencial = ?");
    }

    private static final String[] AUTH_WHITELIST = {
        // -- Swagger UI v2
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        // -- Swagger UI v3 (OpenAPI)
        "/v3/api-docs/**",
        "/swagger-ui/**",
        // other public endpoints
        "/h2-console/**",
 };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .antMatchers(
                        "/",
                        "/logout",
                        "/generic/**",
                        "/candidato/registro",
                        "/empresa/registro",
                        "/oferta/**","/images/**",
                        "/habilidad/**")
                .permitAll()
                
                .antMatchers(AUTH_WHITELIST).permitAll()

                .antMatchers("/app/candidato/**").hasAnyAuthority("ROLE_CANDIDATO")
                .antMatchers("/app/empresa/**").hasAnyAuthority("ROLE_EMPRESA")

                .anyRequest().authenticated()

                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/afterlogin", true)          
                .permitAll()
                
                .and().logout()
                .logoutSuccessUrl("/")
                .permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}