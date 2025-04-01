package com.thezz9.schedulerjpa.common.logger;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionLogger {

    public static void logSessionInfo(HttpSession session) {
        log.info("session.getId()={}", session.getId());
        log.info("session.getMaxInactiveInterval()={}", session.getMaxInactiveInterval());
        log.info("session.getCreationTime()={}", session.getCreationTime());
        log.info("session.getLastAccessedTime()={}", session.getLastAccessedTime());
        log.info("session.isNew()={}", session.isNew());
    }

}
