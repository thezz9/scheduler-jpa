package com.thezz9.schedulerjpa.common.filter;

import com.thezz9.schedulerjpa.common.logger.SessionLogger;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] ALLOWED_PATHS = {"/", "/api/users/signup", "/api/login", "/api/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // ALLOWED_PATHS에 포함된 URI로 접근시 필터 종료
        if (isAllowedPath(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        log.info("로그인 필터 로직 실행");

        HttpSession session = httpRequest.getSession(false);

        // 로그인 여부 검증
        if (session == null || session.getAttribute("userId") == null) {
            log.warn("로그인되지 않은 사용자 요청: {}", requestURI);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 해주세요.");
        }

        // 세션 정보 출력
        SessionLogger.logSessionInfo(session);

        chain.doFilter(request, response);
    }

    // ALLOWED_PATHS 확인
    private boolean isAllowedPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(ALLOWED_PATHS, requestURI);
    }

}
