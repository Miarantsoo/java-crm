package com.project.javacrm.utils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Getter
@Setter
@Component
public class ModuleUtils {

    private ModelAndView modelAndView;

    @Autowired
    AuthService authService;

    @Autowired
    HttpServletResponse response;

    public ModelAndView setModule(String pathToPage) {
        this.modelAndView = new ModelAndView("module");
        this.modelAndView.addObject("page", pathToPage);
        return this.modelAndView;
    }

}
