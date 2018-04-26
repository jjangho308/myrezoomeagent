package io.rezoome.manager.property;

public abstract class AbstractAgentProperties {
  protected final String ORG_CODE;
  protected final String ORG_NAME;
  protected final String ORG_PASSCODE;
  
  protected final String DB_USER_ID;
  protected final String DB_PASSWORD;
  
  
  public AbstractAgentProperties(String orgCode, String orgName, String orgPasscode, String dbUser, String dbPwd){
    this.ORG_CODE = orgCode;
    this.ORG_NAME = orgName;
    this.ORG_PASSCODE = orgPasscode;
    this.DB_USER_ID = dbUser;
    this.DB_PASSWORD = dbPwd;
  }


  public String getORG_CODE() {
    return ORG_CODE;
  }


  public String getORG_NAME() {
    return ORG_NAME;
  }


  public String getORG_PASSCODE() {
    return ORG_PASSCODE;
  }


  public String getDB_USER_ID() {
    return DB_USER_ID;
  }


  public String getDB_PASSWORD() {
    return DB_PASSWORD;
  }    
    
  
}
