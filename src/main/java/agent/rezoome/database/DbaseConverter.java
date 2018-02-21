package agent.rezoome.database;

import agent.rezoome.vo.OrgRsltVOImpl;
import agent.rezoome.vo.OrgVO;
import agent.rezoome.vo.RzmRsltVO;
import agent.rezoome.vo.RzmVOImpl;

public interface DbaseConverter {

	
  public OrgVO convert(RzmVOImpl rzmVo);

  public RzmRsltVO convert(OrgRsltVOImpl orgVo);
  // public RzmRsltVO convertOrgToRzm(OrgRsltVOImpl orgRsltVo);

}
