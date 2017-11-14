package com.example.research;

import com.example.research.configuration.CoreConfiguration;
import com.example.research.model.Website;
import com.example.research.service.WebsiteService;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootConfiguration
@Import({CoreConfiguration.class, FlywayAutoConfiguration.class})
public class AlexaDescriptor implements CommandLineRunner {

    private static final String ALEXA_TOP_1M_URL = "top-1m.csv";
    private static final Logger LOG = Logger.getLogger(AlexaDescriptor.class);

    private boolean resetAll = false;

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
        Files.lines(Paths.get(resource.toURI())).parallel().map(line -> {
            String[] data = line.split(",");
            Website website = new Website();
            website.setDomain(data[1]);

            return website;
        }).filter(website -> {
            Website old = service.findByDomain(website.getDomain());
            return old != null && resetAll || old == null;
        }).map(website -> {
            // store the site basic info: domain/title/description/keywords/icp
            try {
                Document doc = Jsoup.connect("http://".concat(website.getDomain()))
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip, deflate")
                        .header("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2,ja;q=0.2")
                        .header("AlexaToolbar-ALX_NS_PH", "AlexaToolbar/alx-4.0.1")
                        .header("Connection", "keep-alive")
                        .header("Host", website.getDomain())
                        .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36")
                        .proxy("172.99.0.1", 8123).get();

                setAttribute(
                        doc.select("title"),
                        Element::text,
                        website::setTitle);

                setAttribute(
                        doc.select("meta[name='description']"),
                        element -> element.attr("content"),
                        website::setDescription);

                setAttribute(
                        doc.select("meta[name='keywords']"),
                        element -> element.attr("content"),
                        website::setKeywords);
            } catch (IOException ignore) {
                LOG.error(String.format(
                        "%s(%d) failed because %s",
                        website.getDomain(), website.getRanks(), ignore.getMessage()));
                return null;
            }

            return website;
        }).filter(Objects::nonNull).map(service::upsert).forEach(LOG::info);
    }

    private void setAttribute(
            Elements elements,
            Function<Element, String> function,
            Consumer<String> consumer) {
        if (elements.isEmpty()) {
            return;
        }

        String content = function.apply(elements.get(0));
        if (content.length() > 1024) {
            content = content.substring(0, 1024);
        }

        consumer.accept(content);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AlexaDescriptor.class);
        app.setWebEnvironment(false);
        app.run(args);
    }

}
