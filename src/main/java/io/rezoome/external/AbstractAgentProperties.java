package io.rezoome.external;

public abstract class AbstractAgentProperties {
  public final String ORG_CODE;
  public final String ORG_NAME;
  public final String ORG_PASSCODE;
  
  public final String DB_USER_ID;
  public final String DB_PASSWORD;
  
  public AbstractAgentProperties(String orgCode, String orgName, String orgPasscode, String dbUser, String dbPwd){
    this.ORG_CODE = orgCode;
    this.ORG_NAME = orgName;
    this.ORG_PASSCODE = orgPasscode;
    this.DB_USER_ID = dbUser;
    this.DB_PASSWORD = dbPwd;
  }
  
}
