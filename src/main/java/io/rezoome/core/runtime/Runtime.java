package io.rezoome.core.runtime;

import java.util.HashMap;
import java.util.Map;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.network.entity.RequestPacketEntity;
import io.rezoome.manager.network.entity.RequestRegistrationArgsEntity;
import io.rezoome.manager.network.entity.ResponsePacketEntity;
import io.rezoome.manager.network.http.HttpConnector;
import io.rezoome.manager.provider.ManagerProvider;

public class Runtime {

  public static void main(String[] args) {
    registration();
    // initailize();
  }

  private static void registration() {
    InitialEvent event = InitialEvent.NOT_RUNTIME;

    try {
      // ManagerProvider.network().initialize(event);
      // RequestPacketEntity packetEntity = new RequestPacketEntity();
      // packetEntity.setProtocol("HTTP");
      // ManagerProvider.network().request(packetEntity);

      HttpConnector httpClient = new HttpConnector();
      Map<String, Object> headers = new HashMap<String, Object>();

      RequestPacketEntity requestEntity = new RequestPacketEntity();
      requestEntity.setMid("mid11111");
      requestEntity.setCmd("registration");

      RequestRegistrationArgsEntity argsEntity = new RequestRegistrationArgsEntity();
      argsEntity.setOrgCode("code001");
      argsEntity.setOrgPasscode("passcode");
      argsEntity.setOrgName("orgName");

      requestEntity.setArgs(argsEntity);

      System.out.println(JSON.toJson(requestEntity));
      headers.put("Content-type", "application/json");

      String result = httpClient.sendPost("http://localhost:3000/agent/reg",
          headers, JSON.toJson(requestEntity));

      ResponsePacketEntity responseEntity = JSON.fromJson(result, ResponsePacketEntity.class);
      System.out.println(responseEntity.toString());

      if (!"200".equals(responseEntity.getCode())) {
        System.out.println("CONNECTION ERROR!");
        System.exit(1);
      } else {
        event = InitialEvent.RUNTIME;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private static void initailize() {
    InitialEvent event = InitialEvent.RUNTIME;

    ManagerProvider.clsarrange().initialize(event);
    ManagerProvider.property().initialize(event);
    // network initialize


    ManagerProvider.push().initialize(event);
    ManagerProvider.pushcommand().initialize(event);
    ManagerProvider.mapper().initialize(event);
    ManagerProvider.database().initialize(event);
    ManagerProvider.job().initialize(event);
    ManagerProvider.log().initialize(event);
  }
}
