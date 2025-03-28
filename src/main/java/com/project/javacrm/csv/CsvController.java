package com.project.javacrm.csv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.javacrm.utils.ModuleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("import")
public class CsvController {

    @Autowired
    ModuleUtils moduleUtils;

    @Autowired
    CsvService csvService;

    @GetMapping("imp")
    public ModelAndView goToImport() {
        return moduleUtils.setModule("import/import");
    }


    @PostMapping("imp")
    public String importToDB(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws JsonProcessingException {
        String res = "redirect:/import/imp";
        String rep = csvService.importDataToLaravel(file);
        redirectAttributes.addFlashAttribute("success", rep);
        System.out.println(rep);
        return res;
    }

}
