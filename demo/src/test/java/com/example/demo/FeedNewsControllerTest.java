package com.example.demo;

import com.example.demo.feed.reader.FeedNewsReader;
import com.example.demo.model.FeedNews;
import com.example.demo.repository.IFeedNewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RssdemoApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)

public class FeedNewsControllerTest {

    @Autowired
    private FeedNewsReader feedReader;

    @Autowired
    private IFeedNewsRepository repository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testController() {

        feedReader.read();

        final ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/readFeed", String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());

        List<FeedNews> result = repository.findAllFeedNews();
        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThan(1);

    }
}
