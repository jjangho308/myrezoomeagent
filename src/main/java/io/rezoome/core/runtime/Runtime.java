package io.rezoome.core.runtime;

import io.rezoome.core.ServiceInitializer.InitialEvent;
import io.rezoome.manager.provider.ManagerProvider;

public class Runtime {

  public static void main(String[] args) {
    initailize();
  }

  private static void initailize() {

    
    InitialEvent event = InitialEvent.RUNTIME;
    
    ManagerProvider.clsarrange().initialize(event);
    ManagerProvider.property().initialize(event);
    ManagerProvider.push().initialize(event);
    ManagerProvider.pushcommand().initialize(event);
    ManagerProvider.mapper().initialize(event);
    ManagerProvider.database().initialize(event);
    ManagerProvider.job().initialize(event);
    ManagerProvider.log().initialize(event);
    
  }
}