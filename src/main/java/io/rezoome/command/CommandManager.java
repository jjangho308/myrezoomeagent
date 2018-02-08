package io.rezoome.command;

import java.util.HashMap;
import java.util.Map;

import io.rezoome.commandAction.CommandAction;
import io.rezoome.commandAction.CommandVO;

public class CommandManager {
  
  private Map<Class<? extends CommandVO>, Class<? extends CommandAction>> map = new HashMap<Class<? extends CommandVO>, Class<? extends CommandAction>>();

  public CommandManager(){
    //this.map.put(DBCommandVO.class, DBCommandAction.class);
    
    
  }
  
}
