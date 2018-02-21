package io.rezoome.convert;

import io.rezoome.core.entity.Entity;

public interface ConvertManager {

  public <T extends Entity> T convert(Entity fromEntity, Entity toEntity);

}
