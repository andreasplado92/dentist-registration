package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/registration")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        dentistVisitService.listVisits();
        model.addAttribute("listVisits", dentistVisitService.listVisits());
        model.addAttribute("dentistList", dentistVisitService.getDentistList());
        return "form";
    }
    @GetMapping("/")
    public String showMainPage(DentistVisitDTO dentistVisitDTO, Model model) {
        dentistVisitService.listVisits();
        model.addAttribute("listVisits", dentistVisitService.listVisits());
        return "main";
    }
    @GetMapping("/results")
    public String showDentistVisits(DentistVisitDTO dentistVisitDTO, Model model) {
        dentistVisitService.listVisits();
        model.addAttribute("listVisits", dentistVisitService.listVisits());
        return "results";
    }


    @GetMapping("/viewVisit")
    public String viewVisit(@RequestParam(value = "id") Long id, DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model){
        dentistVisitService.viewVisit(id);
        model.addAttribute("singleListVisit", dentistVisitService.viewVisit(id));
        model.addAttribute("listVisits", dentistVisitService.listVisits());
        model.addAttribute("dentistList", dentistVisitService.getDentistList());
        return "edit";
    }


    @GetMapping("/deleteVisit")
    public String deleteVisit(@RequestParam(value = "id") int id, Model model){
        dentistVisitService.deleteVisit(id);
        dentistVisitService.listVisits();
        //add dentist names list into selectbox
        model.addAttribute("listVisits", dentistVisitService.listVisits());

        return "results";
    }

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model) {
        model.addAttribute("dentistList", dentistVisitService.getDentistList());
        if (bindingResult.hasErrors()) {
            return "form";
        }

        dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitTime());
        return "redirect:/results";
    }

    @PostMapping("/update")
    public String updateRegisterForm(@RequestParam(value = "id") Long id,
                                     @Valid DentistVisitDTO dentistVisitDTO,
                                     BindingResult bindingResult,
                                     Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("singleListVisit", dentistVisitService.viewVisit(id));
            model.addAttribute("dentistList", dentistVisitService.getDentistList());
            return "edit";
        }
        dentistVisitService.updateVisit(
                id,
                dentistVisitDTO.getDentistName(),
                dentistVisitDTO.getVisitTime());
        return "redirect:/results";
    }

    @GetMapping("/search")
    public String searchDentistVisits(@RequestParam(value = "dentist") String dentistName,
                                      DentistVisitDTO dentistVisitDTO,
                                      Model model) {
        dentistVisitService.searchVisitByDentistName(dentistName);
        model.addAttribute("listVisits", dentistVisitService.searchVisitByDentistName(dentistName));
        model.addAttribute("dentistName", dentistName);
        return "searchResults";
    }
    @RequestMapping("/404.html")
    public String render404(Model model) {
        // Add model attributes
        return "404";
    }

}
