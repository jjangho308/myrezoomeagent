package io.rezoome.Database;

import io.rezoome.Vo.OrgRsltVOImpl;
import io.rezoome.Vo.OrgVO;
import io.rezoome.Vo.OrgVOImpl;
import io.rezoome.Vo.RzmRsltVO;
import io.rezoome.Vo.RzmVOImpl;

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
