package com.project.javacrm.invoice;

import ch.qos.logback.core.model.ModelUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.javacrm.paiement.Paiement;
import com.project.javacrm.utils.AuthService;
import com.project.javacrm.utils.ModuleUtils;
import com.project.javacrm.utils.Pagination;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("invoice")
public class InvoiceController {

    ModuleUtils moduleUtils = new ModuleUtils();

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    AuthService authService;

    @Autowired
    HttpServletResponse response;

    @GetMapping("liste")
    public ModelAndView goToPaiement(@RequestParam(name = "page", required = false) Integer page) throws JsonProcessingException {
        try {
            authService.requireUser();
        } catch (Exception e) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = moduleUtils.setModule("invoice/liste");
        Pagination<Invoice> paiements = invoiceService.getPaginateInvoice(page);
        mav.addObject("invoice", paiements);
        return mav;
    }


    @GetMapping("liste-lines")
    public ModelAndView goToInvoiceLines(@RequestParam(name = "page", required = false) Integer page) throws JsonProcessingException {
        try {
            authService.requireUser();
        } catch (Exception e) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = moduleUtils.setModule("invoice/liste-invoice-lines");
        Pagination<InvoiceLine> paiements = invoiceService.getPaginateInvoiceLine(page);
        mav.addObject("invoice-line", paiements);
        return mav;
    }

}
