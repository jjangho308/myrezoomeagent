package DatabaseManager;

import vo.OrgRsltVOImpl;
import vo.OrgVO;
import vo.OrgVOImpl;
import vo.RzmRsltVO;
import vo.RzmVOImpl;

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
