package io.rezoome.external.inha.mapper;

import java.io.IOException;
import java.util.List;

import io.rezoome.constants.ErrorCodeConstants;
import io.rezoome.exception.ServiceException;
import io.rezoome.external.common.entity.AgencyKeyEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.external.common.entity.university.InfoEntity;
import io.rezoome.external.common.mapper.AbstractExternalMapper;
import io.rezoome.external.inha.entity.InhaResponseResultArgsEntity;
import io.rezoome.external.inha.entity.SubIdEntity;

public class InhaDaoMapper extends AbstractExternalMapper {

  @Override
  public AgencyResultEntity getDbDataOfSubID(AgencyKeyEntity entity, String subId) throws ServiceException {
    InhaResponseResultArgsEntity ar = new InhaResponseResultArgsEntity(); 
    InfoEntity info = new InfoEntity();
    info.setUniv_name("인하대학교");
    info.setCert_main_agent("교무처장");    
    info.setMsg1("위 사실을 증명합니다.");
    
    List<AgencyResultEntity> registerRecords = null;
    
    try {
      switch (subId) {
        case SubIdEntity.SUBID_INHA_RCOGC0008:
          ar.setUnivInfo(info);
          registerRecords = daoMgr.getDao().getJolupRecord(entity);
          ar.setRegistList(registerRecords);
          break;
        case SubIdEntity.SUBID_INHA_RCOGC0009:
                  
          ar.setUnivInfo(info);
          List<AgencyResultEntity> scoreRecords = daoMgr.getDao().getScoreRecord(entity);          
          registerRecords = daoMgr.getDao().getJolupRecord(entity);
          
          ar.setScoreList(scoreRecords);
          ar.setRegistList(registerRecords);
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
