package io.rezoome.database;

import io.rezoome.vo.OrgRsltVOImpl;
import io.rezoome.vo.OrgVO;
import io.rezoome.vo.RzmRsltVO;
import io.rezoome.vo.RzmVOImpl;

public interface DbaseConverter {

  /*
   * �̸� ������� ���� ���ѹ� From : ������ ó�� ������ to : ������ ������ ���� Ŭ�����Ʈ �ۺ�Ű �츮�� ������ȸ�� ��û�� ����� �迭(JSON)
   */

  public OrgVO convert(RzmVOImpl rzmVo);

  public RzmRsltVO convert(OrgRsltVOImpl orgVo);
  // public RzmRsltVO convertOrgToRzm(OrgRsltVOImpl orgRsltVo);



}
