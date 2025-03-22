package com.project.javacrm.dashboard;


import com.project.javacrm.utils.ModuleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    ModuleUtils moduleUtils;

    @GetMapping("/")
    public ModelAndView dashboard() {
        ModelAndView mav = moduleUtils.setModule("dashboard/dashboard");
        return mav;
    }

}
