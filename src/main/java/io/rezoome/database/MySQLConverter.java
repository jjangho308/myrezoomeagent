package io.rezoome.database;

import io.rezoome.vo.OrgRsltVOImpl;
import io.rezoome.vo.OrgVOImpl;
import io.rezoome.vo.RzmRsltVO;
import io.rezoome.vo.RzmRsltVOImpl;
import io.rezoome.vo.RzmVOImpl;

public class MySQLConverter extends DbaseConverterImpl {


  public MySQLConverter() {

  }

  public MySQLConverter(RzmVOImpl rzmVo) throws Exception {
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
