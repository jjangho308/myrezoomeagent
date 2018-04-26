package io.rezoome.external.mk.property;

import io.rezoome.manager.property.AbstractAgentProperties;

public class MkProperties extends AbstractAgentProperties {
  private static String ORG_CODE = "code001";
  private static String ORG_NAME = "opic";
  private static String ORG_PASSCODE = "passcode";
  
  private static String DB_USER_ID = "rzroot";
  private static String DB_PASSWORD = "Sgen2018!";


  public MkProperties(){
    super(ORG_CODE, ORG_NAME, ORG_PASSCODE, DB_USER_ID, DB_PASSWORD);
  }
}
