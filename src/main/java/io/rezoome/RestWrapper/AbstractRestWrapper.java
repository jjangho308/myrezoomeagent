package io.rezoome.RestWrapper;

public abstract class AbstractRestWrapper implements RestWrapper{

  public abstract void request();
  public abstract void requestKeepAlive();
  public abstract void requestDataTransfer();
  public abstract void requestLogTransfer();
  public abstract void requestKeyProvision();
  public abstract void requestAuthenticate();
    

}
