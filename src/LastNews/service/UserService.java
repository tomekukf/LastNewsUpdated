package LastNews.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import LastNews.dao.DAOFactory;
import LastNews.dao.UserDAO;
import LastNews.model.User;

public class UserService {
	
	public void addUser(String username, String email,String password) {
		User user= new User();
		user.setUsername(username);
		user.setEmail(email);
		String md5 = encryptPassword(password);
		user.setPassword(md5);
		user.setActive(true);
		
		DAOFactory factory = DAOFactory.getDAOFactory();
		
		UserDAO userDAO= factory.getUserDAO();
		userDAO.create(user);
		
	}
	
	private String encryptPassword(String password) {
		MessageDigest digest=null;
		try {
			digest = MessageDigest.getInstance("MD5");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(password.getBytes());
		String md5Password = new BigInteger(1,digest.digest()).toString(16);
		
		return md5Password;
	}

	public User getUserByUsername(String username) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO= factory.getUserDAO();
		User user= userDAO.getUserByUsername(username);
				return user;
	}
	public User getUserById(long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO= factory.getUserDAO();
		User user= userDAO.read(userId);
				return user;
	}

}
