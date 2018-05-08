package io.rezoome.external.mk.property;

import io.rezoome.external.common.prop.AbstractAgentProperties;

public class MkProperties extends AbstractAgentProperties {
  private static String ORG_ID = "200";
  private static String ORG_NAME = "mk";
  private static String ORG_PASSCODE = "passcode";
  
  public MkProperties(){
    super(ORG_ID, ORG_NAME, ORG_PASSCODE);
  }
}
