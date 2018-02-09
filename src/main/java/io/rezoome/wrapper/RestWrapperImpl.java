package io.rezoome.wrapper;


import io.rezoome.http.HttpManager;
import io.rezoome.http.HttpManagerImpl;

public class RestWrapperImpl extends AbstractRestWrapper {

  private HttpManager httpManager = null;


  private static class Singleton {
    private static final RestWrapperImpl instance = new RestWrapperImpl();
  }

  public static RestWrapperImpl getInstance() {
    System.out.println("create instance");
    return Singleton.instance;
  }

  public RestWrapperImpl() {
    httpManager = new HttpManagerImpl("https://", "HTTPS");
  }

  @Override
  public void request() {
    httpManager.callHttpPostJson(null, "jsonstring");
  }

  @Override
  public void requestKeepAlive() {
    // TODO Auto-generated method stub

  }

  @Override
  public void requestDataTransfer() {
    // TODO Auto-generated method stub

  }

  @Override
  public void requestLogTransfer() {
    // TODO Auto-generated method stub

  }

  @Override
  public void requestKeyProvision() {
    // TODO Auto-generated method stub

  }

  @Override
  public void requestAuthenticate() {
    // TODO Auto-generated method stub

  }


}
