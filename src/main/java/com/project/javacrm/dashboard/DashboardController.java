package com.project.javacrm.dashboard;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.javacrm.invoice.InvoiceService;
import com.project.javacrm.offer.OfferService;
import com.project.javacrm.paiement.PaiementService;
import com.project.javacrm.utils.AuthService;
import com.project.javacrm.utils.ModuleUtils;
import jakarta.servlet.http.HttpServletResponse;
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

    @Autowired
    PaiementService paiementService;

    @Autowired
    OfferService offerService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    DashboardService dashboardService;

    @Autowired
    AuthService authService;

    @Autowired
    HttpServletResponse response;

    @GetMapping("/")
    public ModelAndView dashboard() throws JsonProcessingException {
        try {
            authService.requireUser();
        } catch (Exception e) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = moduleUtils.setModule("dashboard/dashboard");
        mav.addObject("totalPaiement", paiementService.getTotalPaiement());
        mav.addObject("totalPrixInvoice", invoiceService.getTotalPrix());
        mav.addObject("totalPrixOffre", offerService.getTotalPrix());
        mav.addObject("totalInvoice", offerService.totalOffers());
        mav.addObject("totalOffer", invoiceService.totalInvoice());
        mav.addObject("dashOffer", dashboardService.getChartOffers());
        mav.addObject("dashPayment", dashboardService.getChartPayment());
        mav.addObject("dashTask", dashboardService.getChartTask());
        return mav;
    }

}
