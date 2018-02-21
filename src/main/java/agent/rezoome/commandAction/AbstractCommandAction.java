package agent.rezoome.commandAction;


public abstract class AbstractCommandAction<T extends CommandVO> implements CommandAction {

  T commandVo;
  
  public AbstractCommandAction(T commandVo) {
    // TODO Auto-generated constructor stub
    this.commandVo = commandVo;
    
  }
  
  public void invokeCommand(T commandVo) {
    // TODO Auto-generated method stub
    
    this.invokeCommandInternal(commandVo);
  }
  
  protected abstract void invokeCommandInternal(T commandVo);
  
  
  
}
