package com.pixelus.web.view;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.Locale;

public class JsonViewResolver implements ViewResolver {

  @Override
  public View resolveViewName(String viewName, Locale locale) throws Exception {
    MappingJacksonJsonView view = new MappingJacksonJsonView();
    view.setPrettyPrint(true);

    return view;
  }
}