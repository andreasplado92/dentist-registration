package com.cgi.dentistapp.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andreas on 16.04.2017.
 */
@Controller
public class RedirectUnknownUrls implements ErrorController {



    @GetMapping("/error")
    public String errorPage(HttpServletResponse response, Model model){

        return "404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}