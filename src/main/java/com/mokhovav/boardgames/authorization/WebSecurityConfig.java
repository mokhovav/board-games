package com.mokhovav.boardgames.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.SortedMap;
import java.util.TreeMap;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //for PreAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /** Listener*/
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    private AuthenticationSuccessHandler successHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }



    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("admin1").password(passwordEncoder().encode("admin1Pass")).roles("ADMIN");
        // @formatter:on
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*","/invalidSession*", "/sessionExpired*", "/foo/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(successHandler())
                .failureUrl("/login.html?error=true")
                .and()
                .logout().deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400)
                .and()
                .sessionManagement()
                .sessionFixation().migrateSession()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/invalidSession.html")
                .maximumSessions(2)
                .expiredUrl("/sessionExpired.html");








        /**
         * always – a session will always be created if one doesn't already exist
         * ifRequired – a session will be created only if required (default)
         * never – the framework will never create a session itself but it will use one if it already exists
         * stateless – no session will be created or used by Spring Security
         * */
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        /**
         * To enable the scenario which allows multiple concurrent sessions for the same user
         * */
        http.sessionManagement().
                maximumSessions(1);

        /**
         * After the session has timed out, if the user sends a request with an expired session id,
         *  they will be redirected to a URL configurable via the namespace
         *  */
//        http.sessionManagement()
//                .expiredUrl("/sessionExpired.html");

        /**
         * Similarly, if the user sends a request with a session id which is not expired,
         *  but entirely invalid, they will also be redirected to a configurable URL
         *  */
        http.sessionManagement()
                .invalidSessionUrl("/invalidSession.html");

        /**
         * The framework offers protection against typical Session Fixation attacks
         *  by configuring what happens to an existing session when the user tries to authenticate again
         *  */
        http.sessionManagement()
                .sessionFixation().migrateSession();





        sessionCreationPolicy(http);    // set session creation policy
        logout(http);                   // logout related configuration
        exceptionHandling(http);        // exception handling
        tokenAuthentication(http);      // configure token authentication filter
        csrf(http);                     // CSRF configuration
        cors(http);                     // CORS configuration
        authorizeRequests(http);        // authorize requests
        otherConfigurations(http);      // override this to add more configurations

        SortedMap map = new TreeMap();
        map.get(1);
    }

    protected void sessionCreationPolicy(HttpSecurity http) throws Exception {
        /**
         * always – a session will always be created if one doesn't already exist
         * ifRequired – a session will be created only if required (default)
         * never – the framework will never create a session itself but it will use one if it already exists
         * stateless – no session will be created or used by Spring Security
         * */

//
//        http.sessionManagement().invalidSessionUrl();



    }






//            http
//                    .authorizeRequests()
//                    .antMatchers( "/","/signUp","/public/**")
//                    .permitAll()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .failureUrl("/login?error=true")
//                    .usernameParameter("userName")
//                    .passwordParameter("pwd")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .deleteCookies("RememberUser")
//                    .logoutSuccessUrl("/")
//                    .permitAll()
//                .and()
//                    .rememberMe().key("rememberUser")
//                .and()
//                    .csrf()
//                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());




    /**
     * Logout related configuration
     */
    protected void logout(HttpSecurity http) throws Exception {

        http
                .logout().disable(); // we are stateless; so /logout endpoint not needed
    }


    /**
     * Configures exception-handling
     */
    protected void exceptionHandling(HttpSecurity http) throws Exception {

        http
                .exceptionHandling()

                /***********************************************
                 * To prevent redirection to the login page
                 * when someone tries to access a restricted page
                 **********************************************/
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }


    /**
     * Configuring token authentication filter
     */
    protected void tokenAuthentication(HttpSecurity http) {

//        http.addFilterBefore(new LemonCommonsWebTokenAuthenticationFilter(blueTokenService),
//                UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * Disables CSRF. We are stateless.
     */
    protected void csrf(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
    }


    /**
     * Configures CORS
     */
    protected void cors(HttpSecurity http) throws Exception {

        http
                .cors();
    }


    /**
     * URL based authorization configuration. Override this if needed.
     */
    protected void authorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/**").permitAll();
    }


    /**
     * Override this to add more http configurations,
     * such as more authentication methods.
     *
     * @param http
     * @throws Exception
     */
    protected void otherConfigurations(HttpSecurity http)  throws Exception {

    }

}
