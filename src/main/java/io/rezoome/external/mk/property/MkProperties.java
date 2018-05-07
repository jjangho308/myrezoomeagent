package io.rezoome.external.mk.property;

import io.rezoome.external.common.prop.AbstractAgentProperties;

public class MkProperties extends AbstractAgentProperties {
  private static String ORG_ID = "200";
  private static String ORG_NAME = "mk";
  private static String ORG_PASSCODE = "passcode";
  
  private static String DB_USER_ID = "rzroot";
  private static String DB_PASSWORD = "Sgen2018!";
  
  public MkProperties(){
    super(ORG_ID, ORG_NAME, ORG_PASSCODE, DB_USER_ID, DB_PASSWORD);
  }
}
