package com.boot.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {
    public static void removeSession() {
        try {

            HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getSession();
            httpSession.removeAttribute("message");
        } catch (Exception e) {
            System.out.println("error occured while removing message");
            e.printStackTrace();
        }
    }
}
