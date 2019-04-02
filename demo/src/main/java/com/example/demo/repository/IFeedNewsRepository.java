package com.example.demo.repository;

import com.example.demo.model.FeedNews;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface IFeedNewsRepository {

    public long insertNews(FeedNews feedNews);

    public Optional<FeedNews> findFeedNewsById(long id);

    public List<FeedNews> findAllFeedNews();

    public String getLastPublishedDate();

    public List<FeedNews> listLastTenNews();
}
