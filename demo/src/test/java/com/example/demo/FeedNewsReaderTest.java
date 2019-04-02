package com.example.demo;

import com.example.demo.feed.reader.FeedNewsReader;
import com.example.demo.model.FeedNews;
import com.example.demo.repository.FeedNewsRepository;
import com.example.demo.repository.IFeedNewsRepository;
import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = FeedNewsRepository.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RssdemoApplication.class)
public class FeedNewsReaderTest {

    @SpyBean
    private FeedNewsReader feedReader;

    @Autowired
    private IFeedNewsRepository repository;


    @Test
    public void jobRuns() {
        await().atMost(Duration.ONE_MINUTE)
                .untilAsserted(() -> verify(feedReader, times(2)).read());

        List<FeedNews> result = repository.findAllFeedNews();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(11);
    }
}
