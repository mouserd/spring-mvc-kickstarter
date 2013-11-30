package com.pixelus.web.com.pixelus.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/spring-context.xml")
public class SampleControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setup() {

    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void shouldGetJspView()
      throws Exception {

    mockMvc.perform(get("/").accept(TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(view().name("/jspSample"))
        .andExpect(model().attribute("staticProperty", "static property value"))
        .andExpect(model().attribute("dynamicProperty", "dynamic property value"));
  }

  @Test
  public void shouldGetFreemarkerView()
      throws Exception {

    mockMvc.perform(get("/freemarker").accept(TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(view().name("/freemarkerSample"))
        .andExpect(model().attribute("staticProperty", "static property value"))
        .andExpect(model().attribute("dynamicProperty", "dynamic property value"));
  }

  @Test
  public void shouldGetJsonResponse() throws Exception {

    mockMvc.perform(get("/json").accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("staticProperty", is("static property value")))
        .andExpect(jsonPath("dynamicProperty", is("dynamic property value")));
  }
}