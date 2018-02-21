package agent.rezoome.manager.job.entity;

import java.util.concurrent.Callable;

import agent.rezoome.core.entity.Action;
import agent.rezoome.manager.job.JobResult;

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