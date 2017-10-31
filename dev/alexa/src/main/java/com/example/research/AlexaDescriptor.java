package com.example.research;

import com.example.research.model.Website;
import com.example.research.service.WebsiteService;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@SpringBootConfiguration
@Import(CoreConfiguration.class)
public class AlexaDescriptor implements CommandLineRunner {

    private static final String ALEXA_TOP_1M_URL = "top-1m.csv";
    private static final Logger LOG = Logger.getLogger(AlexaDescriptor.class);

    @Autowired
    private WebsiteService service;

    @Override
    public void run(String... args) throws Exception {
        // read alexa top 1m file
        URL resource = getClass().getClassLoader().getResource(ALEXA_TOP_1M_URL);
        if (resource == null) {
            throw new RuntimeException();
        }

        // visit all websites
        Files.lines(Paths.get(resource.toURI())).limit(10).map(line -> {
            // store the site basic info: domain/title/description/keywords/icp
            String[] data = line.split(",");
            Website website = new Website();
            website.setRank(Integer.valueOf(data[0]));
            website.setDomain(data[1]);

            try {
                Document doc = Jsoup.connect("http://".concat(website.getDomain())).get();
                Elements title = doc.select("title");
                website.setTitle(title.get(0).text());

                Elements description = doc.select("meta[name='description']");
                if (!description.isEmpty()) {
                    website.setDescription(description.get(0).attr("content"));
                }

                Elements keywords = doc.select("meta[name='keywords']");
                if (!keywords.isEmpty()) {
                    website.setKeywords(keywords.get(0).attr("content"));
                }
            } catch (IOException ignore) {
                LOG.error(String.format("%s failed because %s", website.getDomain(), ignore.getMessage()));
                return null;
            }

            return website;
        }).filter(Objects::nonNull).peek(LOG::info).forEach(service::upsert);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AlexaDescriptor.class);
        app.setWebEnvironment(false);
        app.run(args);
    }

}
