package io.rezoome.RestWrapper;


import io.rezoome.http.HttpTransferImpl;

public class RestWrapperImpl extends AbstractRestWrapper {

  private HttpTransferImpl httpObj = null;
  

  private static class Singleton {
    private static final RestWrapperImpl instance = new RestWrapperImpl();
  }
  
  public static RestWrapperImpl getInstance () {
    System.out.println("create instance");
    return Singleton.instance;
  }
  
  public RestWrapperImpl(){
    httpObj = new HttpTransferImpl();
  }
  
  @Override
  public void request(){
    
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
