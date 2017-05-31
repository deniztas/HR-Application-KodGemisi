package hr.kodgemisi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.kodgemisi.repository.JobRepository;
import hr.kodgemisi.classes.Job;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepo;

	public Job create(Job job){
		return jobRepo.save(job);
	}
	
	public Job findID(Integer id){
		return jobRepo.findOne(id);
	}
	
	public String deleteJob(int jobID){
		Job job = jobRepo.findOne(jobID);
		String jobTitle = job.getJobTitle();
		jobRepo.delete(jobID);
		return jobTitle;	
	}
	
	public Iterable<Job> findAll(){		
		return jobRepo.findAll();
	}
	
}
