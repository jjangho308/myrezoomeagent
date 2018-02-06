package DatabaseManager;

import vo.OrgRsltVOImpl;
import vo.OrgVO;
import vo.RzmRsltVO;
import vo.RzmVOImpl;

public class DbaseConverterImpl implements DbaseConverter{


  protected RzmVOImpl rzmVo = null;

  public DbaseConverterImpl(RzmVOImpl rzmVo) throws Exception {
    
    // TODO Auto-generated constructor stub
    this.rzmVo  = rzmVo;
  }
 
  @Override
  public OrgVO convertRzmToOrg() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RzmRsltVO convertOrgToRzm(OrgRsltVOImpl orgRsltVo){
    // TODO Auto-generated method stub
    return null;
  }

}
