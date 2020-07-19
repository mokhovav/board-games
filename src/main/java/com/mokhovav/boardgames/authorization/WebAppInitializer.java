package com.mokhovav.boardgames.authorization;

import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import java.util.EnumSet;

/**Сan be configured in properties*/
@Component
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        /**httpOnly: if true then browser script won't be able to access the cookie*/
        servletContext.getSessionCookieConfig().setHttpOnly(true);
        /**secure: if true then the cookie will be sent only over HTTPS connection*/
        servletContext.getSessionCookieConfig().setSecure(true);
        /**Prevent Using URL Parameters for Session Tracking*/
        servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
        /**Session timeout value*/
        servletContext.setSessionTimeout(15);
    }
}
