package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.mvc.RequestMethod;
import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;

@Controller
public class HomeController{

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";
    }
}
