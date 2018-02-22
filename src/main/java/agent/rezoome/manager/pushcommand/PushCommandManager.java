package agent.rezoome.manager.pushcommand;

import agent.rezoome.manager.Manager;
import agent.rezoome.manager.pushcommand.entity.PushCommandAction;
import agent.rezoome.manager.pushcommand.entity.PushCommandEntity;
import agent.rezoome.manager.pushcommand.entity.PushCommandResult;

/**
 * Push command manager. <br />
 * 
 * @since 1.0.0
 * @author Saver
 *
 */
public interface PushCommandManager extends Manager {
	public PushCommandResult invokeCommand(PushCommandEntity command);
	public <T extends PushCommandEntity> PushCommandAction<T> getAction(T command);
}