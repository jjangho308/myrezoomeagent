package io.rezoome.manager.arrange;

import io.rezoome.manager.job.entity.JobEntity;
import io.rezoome.manager.job.iorequest.IORequestJobAction;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
import io.rezoome.manager.network.entity.response.ResponseArgsEntity;
import io.rezoome.manager.network.entity.response.ResponseAuthenticationArgsEntity;
import io.rezoome.manager.network.entity.response.ResponseHealthCheckArgsEntity;
import io.rezoome.manager.network.entity.response.ResponseSearchRecordArgsEntity;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
import io.rezoome.manager.pushcommand.entity.search.SearchCommandAction;
import io.rezoome.manager.pushcommand.entity.search.SearchCommandEntity;

/**
 * Class mapper dummy. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
final class ClassMapper {
  static void setEntityKeyMap(ClassArrangeManagerImpl arranger) {

    arranger.addEntityKeyMap(PushCommandEntity.class, "SearchRecord", SearchCommandEntity.class);

    arranger.addActionMap(PushCommandEntity.class, SearchCommandEntity.class, SearchCommandAction.class);

    arranger.addEntityKeyMap(JobEntity.class, "SearchRecord", IORequestJobEntity.class);

    arranger.addActionMap(JobEntity.class, IORequestJobEntity.class, IORequestJobAction.class);

        
    // REZOOME INTERNAL HTTP RESPONSE
    arranger.addEntityKeyMap(ResponseArgsEntity.class, "Auth", ResponseAuthenticationArgsEntity.class);
    arranger.addEntityKeyMap(ResponseArgsEntity.class, "HealthCheck", ResponseHealthCheckArgsEntity.class);
    arranger.addEntityKeyMap(ResponseArgsEntity.class, "KeyProvision", ResponseHealthCheckArgsEntity.class);
    arranger.addEntityKeyMap(ResponseArgsEntity.class, "SearchRecord", ResponseSearchRecordArgsEntity.class);
    
    
      
  }
}
