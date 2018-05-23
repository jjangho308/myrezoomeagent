package io.rezoome.external.kmu.mapper;

import java.io.IOException;
import java.util.List;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.entity.university.InfoEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.kmu.entity.KmuResponseResultArgsEntity;
import io.rezoome.external.kmu.property.KmuSubIDs;

public class KmuDaoMapper extends AbstractExternalMapper{

  @Override
  public AgencyResultEntity getDbDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {
    KmuResponseResultArgsEntity ar = new KmuResponseResultArgsEntity(); 
    try {
      switch (subId) {
        case KmuSubIDs.SUBID_KMU_RCOGC0010:
          
          InfoEntity info1 = new InfoEntity();
          info1.setUniv_name("계명대학교");
          info1.setCert_main_agent("교무처장");
          info1.setMsg1("위 사실을 증명합니다.");
          
          ar.setUnivInfo(info1);
          
          
          List<AgencyResultEntity> registerRecords = daoMgr.getDao().getJolupRecord(entity);
          ar.setRegistList(registerRecords);
          break;
        case KmuSubIDs.SUBID_KMU_RCOGC0011:
          InfoEntity info2 = new InfoEntity();
          info2.setUniv_name("계명대학교");
          info2.setCert_main_agent("교무처장");
          info2.setMsg1("위 사실을 증명합니다.");
          
          ar.setUnivInfo(info2);
          List<AgencyResultEntity> scoreRecords = daoMgr.getDao().getJolupRecord(entity);
          scoreRecords = daoMgr.getDao().getScoreRecord(entity);
          
          ar.setScoreList(scoreRecords);
          List<AgencyResultEntity> scoreStatisticRecords = daoMgr.getDao().getScoreStatisticRecord(entity);
          
          ar.setScoreStatisticList(scoreStatisticRecords);
          
          break;
        default:
          throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNDEFINED);
      }
    } catch (IOException e) {
      throw new ServiceException(ErrorCodeConstants.ERROR_CODE_UNABLE_TO_GET_DATA, e);
    }
    
    //System.out.println("RESULT : " + ar);
    //return result;
    return ar;
  }

  @Override
  public String getViaDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  
  
  
}


