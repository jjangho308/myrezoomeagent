package io.rezoome.commandAction;


public abstract class AbstractCommandAction<T extends CommandVO> implements CommandAction {

  T commandVo;
  
  public AbstractCommandAction(T commandVo) {
    // TODO Auto-generated constructor stub
    this.commandVo = commandVo;
    
  }
  
  public void invokeCommand(T commandVo) {
    // TODO Auto-generated method stub
    
    // 공통 로직
    
    // 개별 로직
    this.invokeCommandInternal(commandVo);
  }
  
  protected abstract void invokeCommandInternal(T commandVo);
  
  
  
}
