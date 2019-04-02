package com.example.demo.repository;

import com.example.demo.model.FeedNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class FeedNewsRepository implements IFeedNewsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class toNews implements RowMapper<FeedNews> {
        public FeedNews mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeedNews feedNewsResult = new FeedNews();
            feedNewsResult.setNewsId(rs.getInt("id"));
            feedNewsResult.setAuthor(rs.getString("author"));
            feedNewsResult.setDescriptionValue(rs.getString("description_value"));
            feedNewsResult.setLink(rs.getString("link"));
            feedNewsResult.setTitle(rs.getString("title"));
            feedNewsResult.setPublishedDate(rs.getString("published_date"));
            feedNewsResult.setRetrievedDate(rs.getString("retrieved_date"));
            return feedNewsResult;
        }
    }

    @Override
    public String getLastPublishedDate() {
        return jdbcTemplate.queryForObject("SELECT published_date FROM news ORDER BY id DESC LIMIT 1", String.class);
    }

    @Override
    public long insertNews(FeedNews feedNews) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO news (author, link, description_value, title, published_date, retrieved_date) " + " values(?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, feedNews.getAuthor());
            ps.setString(2, feedNews.getLink());
            ps.setString(3, feedNews.getDescriptionValue());
            ps.setString(4, feedNews.getTitle());
            ps.setString(5, feedNews.getPublishedDate());
            ps.setString(6, new Date().toString());
            return ps;
        }, holder);

        return holder.getKey().longValue();
    }


    @Override
    public Optional<FeedNews> findFeedNewsById(long id) {
        String sql = "SELECT  * FROM news WHERE id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, new Object[] { id }, new toNews()));
    }


    @Override
    public List<FeedNews> findAllFeedNews() {
        String sql = "SELECT * FROM news";
        return this.jdbcTemplate.query(sql, new toNews());
    }

    @Override
    public List<FeedNews> listLastTenNews() {
        return jdbcTemplate.query("SELECT  * FROM news ORDER BY id DESC LIMIT 10", new toNews());
    }
}
