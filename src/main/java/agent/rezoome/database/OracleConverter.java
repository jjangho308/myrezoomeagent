package agent.rezoome.database;

import agent.rezoome.vo.OrgRsltVOImpl;
import agent.rezoome.vo.OrgVOImpl;
import agent.rezoome.vo.RzmRsltVO;
import agent.rezoome.vo.RzmRsltVOImpl;
import agent.rezoome.vo.RzmVOImpl;

public class OracleConverter extends DbaseConverterImpl {

  public OracleConverter() {

  }

  public OracleConverter(RzmVOImpl rzmVo) throws Exception {
    super(rzmVo);
    // TODO Auto-generated constructor stub
  }

  @Override
  public OrgVOImpl convert(RzmVOImpl rzmVo) {
    // TODO Auto-generated method stub

    OrgVOImpl orgVo = new OrgVOImpl();

    orgVo.setSex(super.rzmVo.getSex());
    orgVo.setBirth(super.rzmVo.getBirth());
    orgVo.setName(super.rzmVo.getName());
    orgVo.setOrgKey(super.rzmVo.getOrgKey());

    return orgVo;
  }

  @Override
  public RzmRsltVO convert(OrgRsltVOImpl orgRsltVo) {
    // TODO Auto-generated method stub
    RzmRsltVO rzmRsltVo = new RzmRsltVOImpl();

    return rzmRsltVo;
  }

}
