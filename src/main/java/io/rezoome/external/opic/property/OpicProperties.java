package io.rezoome.external.opic.property;

import io.rezoome.manager.property.AbstractAgentProperties;

public class OpicProperties extends AbstractAgentProperties {
  private static String ORG_CODE = "code001";
  private static String ORG_NAME = "opic";
  private static String ORG_PASSCODE = "passcode";
  
  private static String DB_USER_ID = "rezoomer";
  private static String DB_PASSWORD = "Sgen2018!";


  public OpicProperties(){
    super(ORG_CODE, ORG_NAME, ORG_PASSCODE, DB_USER_ID, DB_PASSWORD);
  }
}
