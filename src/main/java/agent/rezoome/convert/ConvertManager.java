package agent.rezoome.convert;

import agent.rezoome.core.entity.Entity;

public interface ConvertManager {

  public <T extends Entity> T convert(Entity fromEntity, Entity toEntity);

}
