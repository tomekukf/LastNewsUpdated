package LastNews.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import LastNews.model.Vote;
import LastNews.model.VoteType;
import LastNews.util.ConnectionProvider;

public class VoteDAOImpl implements VoteDAO {

	private static final String CREATE_VOTE ="INSERT INTO vote(news_id,user_id,date,type) VALUES (:news_id,:user_id,:date,:type);";
	
	private static final String READ_VOTE = "SELECT vote_id, news_id, user_id, date, type "
            + "FROM vote WHERE vote_id = :vote_id;";
	private static final String UPDATE_VOTE = "UPDATE vote SET date=:date, type=:type WHERE vote_id=:vote_id;";
	
	 private static final String READ_VOTE_BY_NEWS_USE_IDS = "SELECT vote_id, news_id, user_id, date, type "
	            + "FROM vote WHERE user_id = :user_id AND news_id = :news_id;";
	
	private NamedParameterJdbcTemplate template;
	
	
	public VoteDAOImpl() {
		template=new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	}
	
	@Override
	public Vote create(Vote vote) {
		Vote newVote = new Vote(vote);
		KeyHolder holder= new GeneratedKeyHolder();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("news_id"	, newVote.getNewsId());
		paramMap.put("user_id", newVote.getUserId());
		paramMap.put("date"	,newVote.getDate());
		paramMap.put("type", newVote.getVoteType().toString());
		SqlParameterSource paramSource= new MapSqlParameterSource(paramMap);
		 int update = template.update(CREATE_VOTE, paramSource, holder);
		 if(update >0) {
			 newVote.setId((Long)holder.getKey());
			 
		 }return newVote;
		
		
		
		
		
	}

	@Override
	public Vote read(Long primaryKey) {
		SqlParameterSource paramSource = new MapSqlParameterSource("vote_id",primaryKey);
		Vote vote= template.queryForObject(READ_VOTE, paramSource, new NewVoteMapper());
		return vote;
	}

	@Override
	public boolean update(Vote vote) {
		boolean result=false;
		Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("date", vote.getDate());
		paramMap.put("type", vote.getVoteType().toString());
        paramMap.put("vote_id", vote.getId());
        SqlParameterSource paramSource= new MapSqlParameterSource(paramMap);
        int update=template.update(UPDATE_VOTE, paramSource);
        if(update > 0)
        	result = true;
        
		return result;
	}

	@Override
	public boolean delete(Long key) {
		return false;
	}

	@Override
	public List<Vote> getAll() {
		return null;
	}

	@Override
	public Vote getVoteByUserIdNewsId(long userId, long newsId) {
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", userId);
        paramMap.put("news_id", newsId);
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        Vote vote = null;
        try {
            vote = template.queryForObject(READ_VOTE_BY_NEWS_USE_IDS, paramSource, new NewVoteMapper());
        } catch(EmptyResultDataAccessException e) {
            //vote not found
        }
        return vote;
	}
	private class NewVoteMapper implements RowMapper<Vote>{

		@Override
		public Vote mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Vote vote = new Vote();
			vote.setId(resultSet.getLong("vote_id"));
			vote.setDate(resultSet.getTimestamp("date"));
			vote.setNewsId(resultSet.getLong("news_id"));
			vote.setUserId(resultSet.getLong("user_id"));
			vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
			
			return vote;
		}
		
		
	}

}
