package hr.kodgemisi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.kodgemisi.classes.JobApp;

@Repository
public interface JobAppRepository extends CrudRepository<JobApp, Integer>{
	
}

