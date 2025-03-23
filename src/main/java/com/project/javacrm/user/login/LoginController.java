package com.project.javacrm.user.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.javacrm.user.LaravelError;
import com.project.javacrm.user.User;
import com.project.javacrm.utils.ModuleUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModuleUtils moduleUtils;

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute Login login, HttpSession session) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", login.getEmail());
        requestBody.put("password", login.getMdp());

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

        Object responseObject;
        try {
            responseObject = objectMapper.readValue(response.getBody(), User.class);
            if(!(responseObject instanceof User)) throw new Exception("Tsy user eh");
        } catch (Exception e) {
            responseObject = objectMapper.readValue(response.getBody(), LaravelError.class);
        }


        ModelAndView modelAndView ;
        if(responseObject instanceof LaravelError) {
            modelAndView = new ModelAndView("redirect:/login");
            modelAndView.addObject("error", responseObject);
        } else {
            modelAndView = new ModelAndView("redirect:/dashboard/");
            session.setAttribute("user", responseObject);
        }

        return modelAndView;
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

