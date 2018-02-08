package io.rezoome.Database;

import io.rezoome.Vo.OrgRsltVOImpl;
import io.rezoome.Vo.OrgVO;
import io.rezoome.Vo.OrgVOImpl;
import io.rezoome.Vo.RzmRsltVO;
import io.rezoome.Vo.RzmVOImpl;

public class DbaseConverterImpl implements DbaseConverter{


  protected RzmVOImpl rzmVo = null;

  
  public DbaseConverterImpl(){
    
  }
  
  public DbaseConverterImpl(RzmVOImpl rzmVo) throws Exception {
    
    // TODO Auto-generated constructor stub
    this.rzmVo  = rzmVo;
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
