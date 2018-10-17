package LastNews.dao;

import java.util.List;

import LastNews.model.News;

public interface NewsDAO  extends GenericDAO<News, Long>{
	
	List<News> getAll();
	

}
