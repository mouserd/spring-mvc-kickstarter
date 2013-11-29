package com.pixelus.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  @Value("${static.property}")
  private String staticProperty;

  @Value("${dynamic.property}")
  private String dynamicProperty;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String displayHome(Model model) {

    model.addAttribute("staticProperty", staticProperty);
    model.addAttribute("dynamicProperty", dynamicProperty);

    return "/home";
  }
}
