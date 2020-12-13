package com.something.my.configuration.interceptor;

import com.something.my.user.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

import static com.something.my.global.utils.Keys.SESSION;

@Log4j2
public class IndexInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        log.info("preHandle() : {}", LocalDateTime.now());
        User session = (User) req.getSession().getAttribute(SESSION);
        if (session != null) {
            res.sendRedirect("/u/dashboard");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle() : {}", LocalDateTime.now());
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion() : {}", LocalDateTime.now());
    }
}
