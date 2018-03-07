package io.rezoome.manager.job.entity;

import io.rezoome.core.entity.Action;

/**
 * Job action. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 * @param <JOB> Job Entity is take charge of this action.
 */
public interface JobAction<JOB extends JobEntity> extends Action<JOB> {
	
}