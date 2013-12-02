package com.pixelus.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.TEXT_HTML;

@RunWith(MockitoJUnitRunner.class)
public class SpringWebAppConfigTest {

  private SpringWebAppConfig springWebAppConfig;

  @Before
  public void setUp() throws Exception {

    springWebAppConfig = new SpringWebAppConfig();
  }

  @Test
  public void shouldAddCssResourceHandler() {

    shouldAddResourceHandlerForResourceLocationName("css");
  }

  @Test
  public void shouldAddJavascriptResourceHandler() {

    shouldAddResourceHandlerForResourceLocationName("js");
  }

  @Test
  public void shouldAddImageResourceHandler() {

    shouldAddResourceHandlerForResourceLocationName("images");
  }

  @Test
  public void shouldConfigureContentNegotiation() {

    ContentNegotiationConfigurer configurer = mock(ContentNegotiationConfigurer.class);
    when(configurer.ignoreAcceptHeader(anyBoolean())).thenReturn(configurer);

    springWebAppConfig.configureContentNegotiation(configurer);

    verify(configurer).ignoreAcceptHeader(true);
    verify(configurer).defaultContentType(TEXT_HTML);
  }

  private void shouldAddResourceHandlerForResourceLocationName(String resourceLocationName) {

    ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
    ResourceHandlerRegistration registration = mock(ResourceHandlerRegistration.class);

    when(registry.addResourceHandler(anyString())).thenReturn(registration);

    springWebAppConfig.addResourceHandlers(registry);

    verify(registry).addResourceHandler("/" + resourceLocationName + "/**");
    verify(registration).addResourceLocations("/" + resourceLocationName + "/");
  }
}
