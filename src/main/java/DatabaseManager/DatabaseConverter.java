package DatabaseManager;

import vo.orgRsltVOImpl;
import vo.orgVO;
import vo.rzmInfoVOImpl;

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

  public orgVO convertRzmToOrg();
  public orgRsltVOImpl convertOrgToRzm();
  
  
}
