package hr.kodgemisi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.kodgemisi.classes.User;
import hr.kodgemisi.repository.UserRepository;



@Service
@Transactional
public class UserService {
	
    @Autowired
    private UserRepository userRepository;
     
    public Iterable<User> findAll(){  	
    	return userRepository.findAll();
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User createNewUser(User user){
    	return userRepository.save(user);
    }
}
