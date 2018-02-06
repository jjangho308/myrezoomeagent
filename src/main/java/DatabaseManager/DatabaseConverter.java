package DatabaseManager;

import vo.OrgRsltVOImpl;
import vo.OrgVO;
import vo.RzmRsltVO;
import vo.RzmVOImpl;

public interface DatabaseConverter {
  
/*  �̸�
  �������
 ����
 ���ѹ�
 From : ������ ó�� ������
 to : ������ ������ ����
  Ŭ�����Ʈ �ۺ�Ű
  �츮�� ������ȸ�� ��û�� ����� �迭(JSON)
  */

  public OrgVO convertRzmToOrg();
  public RzmRsltVO convertOrgToRzm(OrgRsltVOImpl orgRsltVo);
  
  
}
