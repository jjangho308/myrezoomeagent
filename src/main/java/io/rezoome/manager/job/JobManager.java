package io.rezoome.manager.job;

import io.rezoome.manager.Manager;
import io.rezoome.manager.job.entity.JobEntity;

/**
 * Job manager. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public interface JobManager extends Manager {

	/**
	 * Add a job in job queue. <br />
	 * 
	 * @since 1.0.0
	 * @author TACKSU
	 * 
	 * @param job
	 */
	void addJob(JobEntity job);
}