package com.pixelus.web.controller;

import com.pixelus.web.controller.dto.SampleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

  @Value("${static.property}")
  private String staticProperty;

  @Value("${dynamic.property}")
  private String dynamicProperty;

  @RequestMapping(value = "/jspSample", method = RequestMethod.GET)
  public String displayJspSample(Model model) {
    model.addAttribute("staticProperty", staticProperty);
    model.addAttribute("dynamicProperty", dynamicProperty);

    return "/jspSample";
  }

  @RequestMapping(value = "/freemarkerSample", method = RequestMethod.GET)
  public String displayFreemarkerSample(Model model) {
    model.addAttribute("staticProperty", staticProperty);
    model.addAttribute("dynamicProperty", dynamicProperty);

    return "/freemarkerSample";
  }

  @RequestMapping(value = "/jsonSample", method = RequestMethod.GET)
  public @ResponseBody SampleDto displayJsonSample() {
    return new SampleDto(staticProperty, dynamicProperty);
  }
}
