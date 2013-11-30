package com.pixelus.web.controller.dto;

public class SampleDto {

  private String staticProperty;
  private String dynamicProperty;

  public SampleDto(String staticProperty, String dynamicProperty) {

    this.staticProperty = staticProperty;
    this.dynamicProperty = dynamicProperty;
  }

  public String getStaticProperty() {

    return staticProperty;
  }

  public void setStaticProperty(String staticProperty) {

    this.staticProperty = staticProperty;
  }

  public String getDynamicProperty() {

    return dynamicProperty;
  }

  public void setDynamicProperty(String dynamicProperty) {

    this.dynamicProperty = dynamicProperty;
  }
}
