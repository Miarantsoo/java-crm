package com.project.javacrm.home;

import com.project.javacrm.user.LaravelError;
import com.project.javacrm.utils.ModuleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ModuleUtils moduleUtils;

    @GetMapping
    @RequestMapping("/login")
    public ModelAndView login( @ModelAttribute("error") @RequestParam(required = false) LaravelError error ) {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping
    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mav = moduleUtils.setModule("dashboard/dashboard");
        return mav;
    }

}
