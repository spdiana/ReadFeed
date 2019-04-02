package com.example.demo.feed.reader;

import com.example.demo.helper.HelperDate;
import com.example.demo.model.FeedNews;
import com.example.demo.repository.IFeedNewsRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Component
public class FeedNewsReader {

    private static final Logger logger = LoggerFactory.getLogger(FeedNewsReader.class);

    @Autowired
    private IFeedNewsRepository repository;

    //@Scheduled(fixedRate = 10000)
    //@Scheduled(fixedRate = 60000)
    @Scheduled(fixedRate = 1800000)
    public void read() {

        Reader reader = null;
        try {

            URL url = new URL("https://www.nytimes.com/svc/collections/v1/publish/https://www.nytimes.com/section/world/rss.xml");

            SyndFeedInput input = new SyndFeedInput();
            reader = new XmlReader(url);
            SyndFeed feed = input.build(reader);

            List<SyndEntry> list = feed.getEntries();

            Collections.reverse(list);

            for (SyndEntry entry : list) {

                Date publishedDate = HelperDate.convertDate(entry.getPublishedDate().toString());
                Date lastDate = HelperDate.convertDate(repository.getLastPublishedDate());

                if (publishedDate.after(lastDate)) {

                    FeedNews n = new FeedNews(entry.getAuthor(), entry.getLink(), entry.getTitle(), entry.getDescription().getValue(), entry.getPublishedDate().toString());
                    repository.insertNews(n);
                }
            }

            logger.info("FeedNewsReader Class - Schedule Called - " + new Date().toString());

        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        } catch (FeedException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
