package io.rezoome.manager.job.entity;

import io.rezoome.core.entity.annotation.EntityType;
import io.rezoome.manager.pushcommand.entity.search.MemberProfile;

public class SearchJobEntity implements JobEntity{

  private MemberProfile profile = null;
  
  public SearchJobEntity(String username, String birth, String phone, String gender){
    
    this.profile.setBirth(birth);
    this.profile.setGender(gender);
    this.profile.setPhone(phone);
    this.profile.setUsername(username);
    
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
