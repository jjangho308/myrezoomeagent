package io.rezoome.database;

import io.rezoome.vo.OrgRsltVOImpl;
import io.rezoome.vo.OrgVO;
import io.rezoome.vo.RzmRsltVO;
import io.rezoome.vo.RzmVOImpl;

public interface DbaseConverter {

  /*
   * 이름 생년월일 성별 폰넘버 From : 데이터 처음 시작점 to : 데이터 끝나는 지점 클리어언트 퍼블릭키 우리가 정보조회를 요청한 기관의 배열(JSON)
   */

  public OrgVO convert(RzmVOImpl rzmVo);

  public RzmRsltVO convert(OrgRsltVOImpl orgVo);
  // public RzmRsltVO convertOrgToRzm(OrgRsltVOImpl orgRsltVo);



}
