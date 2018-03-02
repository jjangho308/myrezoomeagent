package io.rezoome.manager.job.entity;

import io.rezoome.core.entity.annotation.EntityType;
import io.rezoome.manager.pushcommand.entity.search.MemberProfile;

public class SearchJobEntity implements JobEntity{

  private MemberProfile profile = null;
  
  public SearchJobEntity(MemberProfile profile){
    this.profile = profile;
  }
  
  public MemberProfile getProfile() {
    return profile;
  }
  public void setProfile(MemberProfile profile) {
    this.profile = profile;
  }
  @Override
  public EntityType getAnnotation() {
    // TODO Auto-generated method stub
    return null;
  }

}
