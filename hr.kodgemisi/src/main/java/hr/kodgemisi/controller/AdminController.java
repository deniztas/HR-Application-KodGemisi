package hr.kodgemisi.controller;
import java.io.IOException;

import javax.json.Json;


import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hr.kodgemisi.classes.Job;
import hr.kodgemisi.classes.User;
import hr.kodgemisi.service.JobAppService;
import hr.kodgemisi.service.JobService;

@Controller
@RequestMapping(path="/admin")
public class AdminController {

	@Autowired
	private JobService jobService;

	@Autowired
	private JobAppService jobAppService;
	
	@RequestMapping(path="", method = RequestMethod.GET)
	public ModelAndView testMestod(HttpServletRequest request, HttpServletResponse response) throws IOException{ //only admin can go admin page
		boolean flag=true;
		
		try{
			HttpSession session = request.getSession();
		     String username = (String)session.getAttribute("username");			 
			 
			 
			 if(username!="admin"){
				 flag=false;	
			 }			
		}
		catch (Exception e) {
			
		}
		if(flag==true){
			return listJobs();			
		}					
		else
			return goHomePage();
		}
	
	public ModelAndView listJobs(){

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin");
		mav.addObject("jobs", jobService.findAll());
		return mav;
	}
	
	public ModelAndView goHomePage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homepage");
		return mav;
	}
	
	@RequestMapping(path="/create", method = RequestMethod.POST,produces="application/json")
	public @ResponseBody String addJob(@RequestParam String jobTitle, @RequestParam String jobDesc,@RequestParam int numOfHired, @RequestParam String lastDate) {
		

		Job newJob = new Job();
		newJob.setJobTitle(jobTitle);
		newJob.setJobDescription(jobDesc);
		newJob.setNumberOfPersonToHire(numOfHired);
		newJob.setLastApplicationDate(lastDate);

		jobService.create(newJob);

		JsonObject result = Json.createObjectBuilder()
				.add("isCreated", true)
				.add("jobTitle", newJob.getJobTitle())
				.build();

		return result.toString();
	}
	
	@RequestMapping(path = "/{id}/delete")
	public String deleteJob(RedirectAttributes redirectAtt, @PathVariable("id") Integer jobId){
		String jobTitle = jobService.deleteJob(jobId);
		redirectAtt.addFlashAttribute("message", jobTitle + " is deleted.");
		return "redirect:/admin";
	}
	
	@RequestMapping(path="/job/{id}/appliers", method = RequestMethod.GET)
	public  ModelAndView listAppliers( @PathVariable("id") Integer jobId){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("applyList");
		mv.addObject("jobs", jobService.findID(jobId));
		return mv;	
	}
		
	@RequestMapping(path = "/apply/{jobId}/{id}/delete", method = RequestMethod.GET)
	public String deleteApply(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id,@PathVariable("jobId") Integer jobId){

		
		String name = jobAppService.deleteJobApp(id);		
		redirectAttributes.addFlashAttribute("message", name + " is deleted.");
		return "redirect:/admin/job/" + jobId + "/appliers";	
	}
	
	

}
