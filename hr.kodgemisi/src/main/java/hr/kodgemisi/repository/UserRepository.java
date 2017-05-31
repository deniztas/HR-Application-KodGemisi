package hr.kodgemisi.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.kodgemisi.classes.User;


@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
}
