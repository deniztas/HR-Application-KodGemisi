package hr.kodgemisi.controller;

import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hr.kodgemisi.classes.Job;
import hr.kodgemisi.classes.JobApp;
import hr.kodgemisi.service.JobService;
import hr.kodgemisi.service.JobAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/homepage")
public class HomepageController {
	
	@Autowired
	private JobService jobSer;
	
	@Autowired
	private JobAppService jobAppService;
	
	@RequestMapping(path="")
	public ModelAndView listJobs(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homepage");
		mav.addObject("jobs", jobSer.findAll());
		
		return mav;
		
	}
	
	@RequestMapping(path="/add", method = RequestMethod.POST,produces="application/json")
	public @ResponseBody String addJobApplication(@RequestParam String name, @RequestParam String surname, @RequestParam String email,@RequestParam String phone, @RequestParam String address, @RequestParam String thoughts, @RequestParam String jobID) {
		
		Job job = new Job();
		job = jobSer.findID(Integer.parseInt(jobID));
		job.setNumberOfPersonToHire(job.getNumberOfPersonToHire() -1);
		JobApp newJobApplication = new JobApp();
		
		newJobApplication.setName(name);
		newJobApplication.setSurname(surname);
		newJobApplication.setEmail(email);
		newJobApplication.setPhone(phone);
		newJobApplication.setAddress(address);		
		newJobApplication.setThoughts(thoughts);
		newJobApplication.setJob(job);
			
		jobAppService.create(newJobApplication);
		
		JsonObject result = Json.createObjectBuilder()
				.add("isCreated", true)
				.add("jobTitle", newJobApplication.getJob().getJobTitle())
				.build();

		return result.toString();
	}
	
	
	
}
