package DatabaseManager;

import vo.OrgRsltVOImpl;
import vo.OrgVO;
import vo.OrgVOImpl;
import vo.RzmRsltVO;
import vo.RzmVOImpl;

public interface DbaseConverter {
  
/*  �̸�
  �������
 ����
 ���ѹ�
 From : ������ ó�� ������
 to : ������ ������ ����
  Ŭ�����Ʈ �ۺ�Ű
  �츮�� ������ȸ�� ��û�� ����� �迭(JSON)
  */

  public OrgVO convert(RzmVOImpl rzmVo);
  public RzmRsltVO convert(OrgRsltVOImpl orgVo);
  //public RzmRsltVO convertOrgToRzm(OrgRsltVOImpl orgRsltVo);
  
  
  
}
