package com.pixelus.web;

import com.pixelus.web.view.JsonViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@PropertySource("classpath:webapp.properties")
@EnableWebMvc
@ComponentScan(basePackages = {"com.pixelus.web.controller", "com.pixelus.web.controller.view"})
public class SpringWebAppConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/images/**").addResourceLocations("/images/");
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
  }

  @Bean
  public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    resolver.setContentNegotiationManager(manager);

    return resolver;
  }

  @Bean(name = "beanNameViewResolver")
  public BeanNameViewResolver getBeanNameViewResolver() {
    BeanNameViewResolver resolver = new BeanNameViewResolver();
    resolver.setOrder(0);

    return resolver;
  }

  @Bean(name = "freemarkerViewResolver")
  public FreeMarkerViewResolver getFreemarkerViewResolver() {
    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
    resolver.setPrefix("");
    resolver.setExposeSpringMacroHelpers(true);
    resolver.setSuffix(".ftl");
    resolver.setOrder(1);

    return resolver;
  }

  @Bean(name = "freemarkerConfig")
  public FreeMarkerConfigurer getFreemarkerConfig() {
    FreeMarkerConfigurer freeMarkerConfig = new FreeMarkerConfigurer();
    freeMarkerConfig.setTemplateLoaderPath("/WEB-INF/freemarker/");

    return freeMarkerConfig;
  }

  @Bean(name = "jspViewResolver")
  public InternalResourceViewResolver getJspViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views");
    resolver.setSuffix(".jsp");
    resolver.setOrder(2);

    return resolver;
  }

  @Bean
  public ViewResolver getJsonViewResolver() {
    return new JsonViewResolver();
  }
}
