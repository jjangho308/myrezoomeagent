package io.rezoome.manager.mapper;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;
import io.rezoome.manager.network.entity.RequestPacketEntity;

public abstract class AbstractMapper {
  /**
   * @author YSY
   */  

  public RequestPacketEntity convert(RzmRsltEntity entity){
    RequestPacketEntity rsltEntity = new RequestPacketEntity();
    
    return rsltEntity;
  }
  public abstract RzmRsltEntity convert(DBRsltEntity entity);
  
}
