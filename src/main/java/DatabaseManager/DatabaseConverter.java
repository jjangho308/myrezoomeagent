package DatabaseManager;

import vo.orgRsltVOImpl;
import vo.orgVO;
import vo.rzmInfoVOImpl;

public interface DatabaseConverter {
  
/*  이름
  생년월일
 성별
 폰넘버
 From : 데이터 처음 시작점
 to : 데이터 끝나는 지점
  클리어언트 퍼블릭키
  우리가 정보조회를 요청한 기관의 배열(JSON)
  */

  public orgVO convertRzmToOrg();
  public orgRsltVOImpl convertOrgToRzm();
  
  
}
