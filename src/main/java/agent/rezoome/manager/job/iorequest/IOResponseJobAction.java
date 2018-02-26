package agent.rezoome.manager.job.iorequest;

import agent.rezoome.manager.database.DatabaseManagerImpl;
import agent.rezoome.manager.database.dao.agency.InhaUniversityDaoImpl;
import agent.rezoome.manager.job.JobResult;
import agent.rezoome.manager.job.entity.AbstractJob;
import agent.rezoome.manager.provider.ManagerProvider;

public class IOResponseJobAction extends AbstractJob<IOResponseJob> {

	public IOResponseJobAction(IOResponseJob entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JobResult processInternal(IOResponseJob entity) {
		
		
		ManagerProvider.database().getConeverter().convert(entity);
		Grade grade = ManagerProvider.database().getDao().getGrade(entity.getName());
		Score score = ManagerProvider.database().getDao().getScore(entity.getName());
		
		Mapper specificMapper = ManagerProvider.database().getMapper();
		RezoomeEntity response = specificMapper.convertToRezoomeEntity(grade, score);
		
		InhaUniversityDaoImpl inha = (InhaUniversityDaoImpl) DatabaseManagerImpl.getInstance().getDao();
		inha.selectGradeById(entity);
	    
	    // Http request
	    
	    
	    // log 
	    ManagerProvider.log();
	    return null;
	}
	
	public Dao getSpecificDao(){
		String daoClass = ManagerProvider.property().getCurrentDatClass(DAO_CLASS_NAME);
		"io.rezoome.agent.db.dao.inha.InhaUnivDao.class"
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Class<?> daoCls = loader.loadClass(daoClass);
		Dao inhaDao = (Dao) daoCls.newInstance();
		return inhaDao;
	}
}
