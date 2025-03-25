package com.project.javacrm.config;


import com.project.javacrm.utils.AuthService;
import com.project.javacrm.utils.ModuleUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("config")
public class ConfigController {

    @Autowired
    ModuleUtils moduleUtils;

    @Autowired
    ConfigService configService;

    @Autowired
    AuthService authService;

    @Autowired
    HttpServletResponse response;

    @GetMapping("/conf")
    public ModelAndView config(@ModelAttribute @RequestParam(required = false) String message) {
        try {
            authService.requireUser();
        } catch (Exception e) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = moduleUtils.setModule("conf/config");
        return mav;
    }

    @PostMapping("/conf")
    public String configurerRemise(@RequestParam(name = "val", required = false) Double val, RedirectAttributes redirectAttributes) {
        if(val == null || val >= 100) {
            String message = "La remise doit avoir une valeur positive inférieur à 100";
            redirectAttributes.addFlashAttribute("message", message);
        } else {
            String message = configService.sendRemiseToLaravel(val);
            redirectAttributes.addFlashAttribute("message", message);
        }
        return "redirect:/config/conf";
    }

}
