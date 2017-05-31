package hr.kodgemisi.classes;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JobApp {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer jobAppId;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String address;
	private String thoughts;
	
	@ManyToOne
	@JoinColumn(name = "jobId")
	private Job job;
	
	public Integer getJobApplicationId() {

		return jobAppId;
	}

	public void setJobApplicationId(Integer jobAppId) {

		this.jobAppId = jobAppId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getThoughts() {
		return thoughts;
	}

	public void setThoughts(String thoughts) {

		this.thoughts = thoughts;
	}
	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}