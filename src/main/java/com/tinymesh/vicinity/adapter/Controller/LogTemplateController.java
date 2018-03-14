package com.tinymesh.vicinity.adapter.Controller;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogTemplateController {


    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public String getForm() {
        return "form";
    }

    @RequestMapping(value = "/devices", method = RequestMethod.POST)
    public String postForm(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");

        if (name == null || name == "") {
            name = "world";
        }
       // model.addAllAttributes("name", name);
       // model.addAllAttributes("title", "Hello, Spring! Response");
        return "";
    }

    @RequestMapping(value = "/log")
    public String log() {
        //get data out of database

        //put data into template
        return "log";
    }
}
