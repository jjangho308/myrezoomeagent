package io.rezoome.manager.job.entity;

import java.util.concurrent.Callable;

import io.rezoome.core.entity.Action;
import io.rezoome.manager.job.JobResult;

/**
 * Job action. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 * @param <JOB> Job Entity is take charge of this action.
 */
public interface JobAction<JOB extends JobEntity> extends Action<JOB>, Callable<JobResult> {
	
}
