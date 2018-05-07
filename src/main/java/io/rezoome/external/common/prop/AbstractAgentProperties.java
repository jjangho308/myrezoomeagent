package io.rezoome.external.common.prop;

public abstract class AbstractAgentProperties {
  public final String ORG_ID;
  public final String ORG_NAME;
  public final String ORG_PASSCODE;
  
  public final String DB_USER_ID;
  public final String DB_PASSWORD;
  
  public AbstractAgentProperties(String orgId, String orgName, String orgPasscode, String dbUser, String dbPwd){
    this.ORG_ID = orgId;
    this.ORG_NAME = orgName;
    this.ORG_PASSCODE = orgPasscode;
    this.DB_USER_ID = dbUser;
    this.DB_PASSWORD = dbPwd;
  }
  
}
