package LastNews.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import LastNews.dao.DAOFactory;
import LastNews.dao.NewsDAO;
import LastNews.model.News;
import LastNews.model.User;

public class NewsService {

	public void addNews(String name, String desc, String url, User user) {
		News news = createNewsObject(name, desc, url, user);
		DAOFactory factory = DAOFactory.getDAOFactory();
		NewsDAO newsDAO = factory.getNewsDAO();
		newsDAO.create(news);

	}

	public News createNewsObject(String name, String desc, String url, User user) {
		News news = new News();
		news.setName(name);
		news.setDescription(desc);
		news.setUrl(url);
		User userCopy = new User(user);
		news.setUser(userCopy);
		news.setTimestamp(new Timestamp(new Date().getTime()));

		return news;

	} 
	
	public boolean updateNews(News news) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		NewsDAO newsDAO = factory.getNewsDAO();
		boolean result = newsDAO.update(news);
	return result;
	}
	
	
	
	public News getNewsById(long newsId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		NewsDAO newsDAO = factory.getNewsDAO();
		News news=newsDAO.read(newsId);
		return news;
	}
	
	public List<News> getAllNewses(){
		return getAllNewses(null);
	}
	
	public List<News> getAllNewses(Comparator<News> comparator){
		DAOFactory factory = DAOFactory.getDAOFactory();
		NewsDAO newsDAO= factory.getNewsDAO();
		List<News> newses = newsDAO.getAll();
				if(comparator != null && newses != null)
					newses.sort(comparator);
		return newses;
	}

}
