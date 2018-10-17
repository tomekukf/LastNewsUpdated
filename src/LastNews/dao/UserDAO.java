package LastNews.dao;

import java.util.List;

import LastNews.model.User;

public interface UserDAO  extends GenericDAO<User, Long>{
	List<User> getAll();
	User getUserByUsername(String username);	

}
