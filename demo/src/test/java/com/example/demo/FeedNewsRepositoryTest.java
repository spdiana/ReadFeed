package com.example.demo;

import com.example.demo.model.FeedNews;
import com.example.demo.repository.IFeedNewsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
public class FeedNewsRepositoryTest {

    private static final String AUTHOR = "Karen Weise";
    private static final String LINK = "https://www.nytimes.com/2019/03/30/health/pain-anxiety-jo-cameron.html";
    private static final String DESCRIPTION_VALUE = "Britain’s breakdown represents a much wider phenomenon";
    private static final String TITLE = "Brexit Mess Reflects Democracy’s New Era of Tear-It-All-Down";
    private static final String PUBLISHED_DATE = "Fri Mar 29 20:28:45 BRT 2019";

    private long id;
    private FeedNews feedNews;

    @Autowired
    private IFeedNewsRepository repository;


    @Before
    public void setUp() {
        feedNews = new FeedNews();
        feedNews.setAuthor(AUTHOR);
        feedNews.setLink(LINK);
        feedNews.setDescriptionValue(DESCRIPTION_VALUE);
        feedNews.setTitle(TITLE);
        feedNews.setPublishedDate(PUBLISHED_DATE);

        id = repository.insertNews(feedNews);
    }

    @Test
    public void testInsert() {
        long id = repository.insertNews(feedNews);

        Optional<FeedNews> result = repository.findFeedNewsById(id);
        assertThat(result).isPresent();
        assertThat(feedNews).hasFieldOrPropertyWithValue("author", AUTHOR);
        assertThat(feedNews).hasFieldOrPropertyWithValue("link", LINK);
        assertThat(feedNews).hasFieldOrPropertyWithValue("descriptionValue", DESCRIPTION_VALUE);
        assertThat(feedNews).hasFieldOrPropertyWithValue("title", TITLE);
        assertThat(feedNews).hasFieldOrPropertyWithValue("publishedDate", PUBLISHED_DATE);
    }

    @Test
    public void testFindByID() {

        Optional<FeedNews> result = repository.findFeedNewsById(id);
        assertThat(result).isPresent();
        assertThat(feedNews).hasFieldOrPropertyWithValue("author", AUTHOR);
        assertThat(feedNews).hasFieldOrPropertyWithValue("link", LINK);
        assertThat(feedNews).hasFieldOrPropertyWithValue("descriptionValue", DESCRIPTION_VALUE);
        assertThat(feedNews).hasFieldOrPropertyWithValue("title", TITLE);
        assertThat(feedNews).hasFieldOrPropertyWithValue("publishedDate", PUBLISHED_DATE);
    }

    @Test
    public void testFindAll() {

        List<FeedNews> result = repository.findAllFeedNews();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }


    @Test
    public void testGetLastPublishedDate() {
        String lastP = repository.getLastPublishedDate();
        assertThat(lastP).isEqualTo(PUBLISHED_DATE);
    }

    @Test
    public void testListLastTenNews() {
        for (int i = 0; i < 20; i++) {
            repository.insertNews(feedNews);
        }

        List<FeedNews> lastP = repository.listLastTenNews();
        assertThat(lastP).isNotNull();
        assertThat(lastP.size()).isEqualTo(10);
    }
}
