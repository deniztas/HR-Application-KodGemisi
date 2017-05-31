package hr.kodgemisi.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.kodgemisi.repository.JobAppRepository;
import hr.kodgemisi.classes.JobApp;

@Service
@Transactional
public class JobAppService {

	@Autowired
	private JobAppRepository jobAppRepo;
	
	
	public JobApp create(JobApp jobApp){
		return jobAppRepo.save(jobApp);
	}
	
	public JobApp findID(Integer id){
		return jobAppRepo.findOne(id);
	}
	
	public String deleteJobApp(int jobAppId){
		JobApp jobApp = jobAppRepo.findOne(jobAppId);
		String jobAppName = jobApp.getName();
		jobAppRepo.delete(jobAppId);
		return jobAppName;
	}
	
}
