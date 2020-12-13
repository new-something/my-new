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
public class TrialRouteInterceptor implements HandlerInterceptor {

    /**
     * session 이 있는 경우, user 에 등록된 shortcut 으로 처리한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        log.info("preHandle() : {}", LocalDateTime.now());
        User session = (User) req.getSession().getAttribute(SESSION);
        // TODO : 동적 처리 로직 구현.. App 연동, Shortcuts 기능이 완료되면.
        if (session != null) {
            res.sendRedirect("/");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) {
        log.info("postHandle() : {}", LocalDateTime.now());
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) {
        log.info("afterCompletion() : {}", LocalDateTime.now());
    }
}
