package hr.kodgemisi.controller;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import hr.kodgemisi.classes.User;
import hr.kodgemisi.service.UserService;


@Controller
@RequestMapping(path="/login")
public class LoginController {
	
	
	@RequestMapping(path="")
	public String AssignUser(HttpServletRequest request){
		User newUser = new User();
		newUser.setUsername("admin");
		newUser.setPassword("admin");
		userSer.createNewUser(newUser);
		
		HttpSession session = request.getSession();
		session.setAttribute("username", newUser.getUsername());
		
		return "login";
		
	}
	
	@Autowired
	UserService userSer;
	
	@RequestMapping(path="/continue", method = RequestMethod.POST,produces="application/json")
	public @ResponseBody String next(@RequestParam String username, @RequestParam String password){
		Iterable <User> query = userSer.findAll();
		if(query != null){
			for(User user : query){
				if(user.getPassword().equals(password) && user.getUsername().equals(username)){
					JsonObject result = Json.createObjectBuilder()
							.add("isLogged", true)
							.add("username", user.getUsername())
							.build();				

					return result.toString();
				}					
			}
		}
		return "false";
	}
	
	
	@RequestMapping(path="/logout")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession newsession = request.getSession(false);
		if(newsession != null){
			newsession.setAttribute("username", null);
			newsession.invalidate();	
		}	
	    response.sendRedirect("/login");
	  }

	}


