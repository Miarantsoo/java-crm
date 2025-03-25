package com.project.javacrm.utils;

import com.project.javacrm.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private HttpSession session;

    public void requireUser() throws ServletException {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            throw new ServletException("You are not logged in");
        }
    }

}
