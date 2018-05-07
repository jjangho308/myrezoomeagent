package io.rezoome.external.opic.property;

import io.rezoome.external.common.prop.AbstractAgentProperties;

public class OpicProperties extends AbstractAgentProperties {
  private static String ORG_ID = "100";
  private static String ORG_NAME = "opic";
  private static String ORG_PASSCODE = "passcode";
  
  private static String DB_USER_ID = "rzroot";
  private static String DB_PASSWORD = "Sgen2018!";
  
  public OpicProperties(){
    super(ORG_ID, ORG_NAME, ORG_PASSCODE, DB_USER_ID, DB_PASSWORD);
  }
}
