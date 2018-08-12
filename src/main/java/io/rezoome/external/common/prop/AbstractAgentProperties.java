package io.rezoome.external.common.prop;

public abstract class AbstractAgentProperties {
  public final String ORG_ID;
  public final String ORG_NAME;
  public final String ORG_PASSCODE;
  
  public AbstractAgentProperties(String ORG_ID, String ORG_NAME, String ORG_PASSCODE){
    this.ORG_ID = ORG_ID;
    this.ORG_NAME = ORG_NAME;
    this.ORG_PASSCODE = ORG_PASSCODE;
  }
  
}
