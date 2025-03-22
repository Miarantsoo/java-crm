package com.project.javacrm.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Getter
@Setter
@Component
public class ModuleUtils {

    private ModelAndView modelAndView;

    public ModelAndView setModule(String pathToPage) {
        this.modelAndView = new ModelAndView("module");
        this.modelAndView.addObject("page", pathToPage);
        return this.modelAndView;
    }

}
