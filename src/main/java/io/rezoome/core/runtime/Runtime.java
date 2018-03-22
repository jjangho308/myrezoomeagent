package io.rezoome.core.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.rezoome.core.ServiceInitializer;
import io.rezoome.core.ServiceInitializer.InitialEvent;

public class Runtime {

  private static final Logger LOG = LoggerFactory.getLogger("AGENT_LOG");

  public static void main(String[] args) {
    LOG.info("Start Runtime");
    initailize();
  }

  private static void initailize() {
    InitialEvent event = InitialEvent.RUNTIME;
    ServiceInitializer.initialize(event);
  }
}
