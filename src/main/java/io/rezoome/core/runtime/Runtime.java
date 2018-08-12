package io.rezoome.core.runtime;

import io.rezoome.core.ServiceInitializer;
import io.rezoome.core.ServiceInitializer.InitialEvent;

public class Runtime {
  public static void main(String[] args) {
    initailize();
  }

  private static void initailize() {
    try {
      ServiceInitializer.initialize(InitialEvent.RUNTIME);
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
