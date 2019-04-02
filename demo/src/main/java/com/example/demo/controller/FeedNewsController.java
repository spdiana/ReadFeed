package com.example.demo.controller;

import com.example.demo.model.FeedNews;
import com.example.demo.repository.IFeedNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedNewsController {

    @Autowired
    private IFeedNewsRepository newsRepository;

    @RequestMapping("/readFeed")
    public List<FeedNews> listNews() {
        return newsRepository.listLastTenNews();
    }

}
