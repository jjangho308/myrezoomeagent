package io.rezoome.external.kyungki.mapper;

import java.io.IOException;
import java.util.List;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.entity.university.InfoEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.kmu.entity.KmuResponseResultArgsEntity;
import io.rezoome.external.kyungki.entity.SubIdEntity;

public class KyungkiDaoMapper extends AbstractExternalMapper {

  @Override
  public AgencyResultEntity getDbDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {
    KmuResponseResultArgsEntity ar = new KmuResponseResultArgsEntity(); 
    InfoEntity info = new InfoEntity();
    info.setUniv_name("인하대학교");
    info.setCert_main_agent("교무처장");
    info.setMsg1("위 사실을 증명합니다.");
    
    try {
      switch (subId) {
        case SubIdEntity.SUBID_KYUNGKI_RCOGC0012:
          ar.setUnivInfo(info);
          List<AgencyResultEntity> registerRecords = daoMgr.getDao().getJolupRecord(entity);
          ar.setRegistList(registerRecords);
          break;
        case SubIdEntity.SUBID_KYUNGKI_RCOGC0013:
                  
          ar.setUnivInfo(info);
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
