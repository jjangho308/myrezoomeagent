package io.rezoome.database;

import io.rezoome.vo.OrgRsltVOImpl;
import io.rezoome.vo.OrgVOImpl;
import io.rezoome.vo.RzmRsltVO;
import io.rezoome.vo.RzmVOImpl;

public class DbaseConverterImpl implements DbaseConverter {


  protected RzmVOImpl rzmVo = null;


  public DbaseConverterImpl() {

  }

  public DbaseConverterImpl(RzmVOImpl rzmVo) throws Exception {

    // TODO Auto-generated constructor stub
    this.rzmVo = rzmVo;
  }

  @Override
  public OrgVOImpl convert(RzmVOImpl rzmVo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RzmRsltVO convert(OrgRsltVOImpl orgVo) {
    // TODO Auto-generated method stub
    return null;
  }

}
