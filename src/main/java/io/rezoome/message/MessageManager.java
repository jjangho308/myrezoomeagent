package io.rezoome.message;

import java.util.concurrent.BlockingQueue;

public interface MessageManager {
  public void prepare(BlockingQueue queue);

  public void run();

}


