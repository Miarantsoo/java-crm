package com.project.javacrm.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.javacrm.invoice.InvoiceLine;
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
@RequestMapping("offer")
public class OfferController {

    ModuleUtils moduleUtils = new ModuleUtils();

    @Autowired
    OfferService offerService;

    @Autowired
    AuthService authService;

    @Autowired
    HttpServletResponse response;

    @GetMapping("liste")
    public ModelAndView goToPaiement(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "message", required = false) String message) throws JsonProcessingException {
        try {
            authService.requireUser();
        } catch (Exception e) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = moduleUtils.setModule("offer/liste");
        Pagination<Offer> paiements = offerService.getPaginateOffer(page);
        mav.addObject("offer", paiements);
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
        Pagination<InvoiceLine> paiements = offerService.getPaginateInvoiceLine(page);
        mav.addObject("invoice-line", paiements);
        return mav;
    }
}
