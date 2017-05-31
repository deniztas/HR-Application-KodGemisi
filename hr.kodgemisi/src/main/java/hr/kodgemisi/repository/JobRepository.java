package hr.kodgemisi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.kodgemisi.classes.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Integer>{
	
}
