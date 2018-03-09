package io.rezoome.manager.pushcommand;

import io.rezoome.manager.Manager;
import io.rezoome.manager.pushcommand.entity.PushCommandAction;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
import io.rezoome.manager.pushcommand.entity.PushCommandResult;

/**
 * Push command manager. <br />
 * 
 * @since 1.0.0
 * @author Saver
 *
 */
public interface PushCommandManager extends Manager, EntityMapper<PushCommandEntity, PushCommandAction<? super PushCommandEntity>> {
	public PushCommandResult invokeCommand(PushCommandEntity command);
}