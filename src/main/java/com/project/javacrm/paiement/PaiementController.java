package com.project.javacrm.paiement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.javacrm.utils.ModuleUtils;
import com.project.javacrm.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("paiement")
public class PaiementController {

    ModuleUtils moduleUtils = new ModuleUtils();

    @Autowired
    PaiementService paiementService;

    @GetMapping("liste")
    public ModelAndView goToPaiement(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "message", required = false) String message) throws JsonProcessingException {
        ModelAndView mav = moduleUtils.setModule("paiement/liste");
        Pagination<Paiement> paiements = paiementService.getPaginatePaiement(page);
        mav.addObject("paiement", paiements);
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView updatePaiement(@RequestParam(name = "external_id") String externaId, @RequestParam(name="amount") Double amount) {
        ModelAndView mav = new ModelAndView("redirect:/paiement/liste");
        String message = paiementService.majMontant(externaId, amount);
        mav.addObject("page", 1);
        mav.addObject("message", message);
        return mav;
    }

    @GetMapping("/delete")
    public ModelAndView deletePaiement(@RequestParam(name = "external_id") String externaId) {
        ModelAndView mav = new ModelAndView("redirect:/paiement/liste");
        String message = paiementService.delPaiement(externaId);
        mav.addObject("page", 1);
        mav.addObject("message", message);
        return mav;
    }

}
