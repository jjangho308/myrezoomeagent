package io.rezoome.Database;

import io.rezoome.Vo.OrgRsltVOImpl;
import io.rezoome.Vo.OrgVO;
import io.rezoome.Vo.OrgVOImpl;
import io.rezoome.Vo.RzmRsltVO;
import io.rezoome.Vo.RzmVOImpl;

public interface DbaseConverter {
  
/*  이름
  생년월일
 성별
 폰넘버
 From : 데이터 처음 시작점
 to : 데이터 끝나는 지점
  클리어언트 퍼블릭키
  우리가 정보조회를 요청한 기관의 배열(JSON)
  */

  public OrgVO convert(RzmVOImpl rzmVo);
  public RzmRsltVO convert(OrgRsltVOImpl orgVo);
  //public RzmRsltVO convertOrgToRzm(OrgRsltVOImpl orgRsltVo);
  
  
  
}
