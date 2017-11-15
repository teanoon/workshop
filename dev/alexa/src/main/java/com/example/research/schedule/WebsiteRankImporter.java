package com.example.research.schedule;

import com.example.research.model.WebsiteRank;
import com.example.research.properties.CustomProperties;
import com.example.research.service.WebsiteRankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WebsiteRankImporter {

    private static final Logger LOG = LoggerFactory.getLogger(WebSiteRankTask.class);

    private static final int batchSize = 10000;

    static final Pattern recordPattern = Pattern.compile("(?<rank>[0-9]*),(?<url>.*)");

    @Autowired
    private WebsiteRankService websiteRankService;

    @Autowired
    private CustomProperties properties;

    void loadData() {
        Map<String, String> entries = properties.getEntries();
        File csvFile = new File(entries.get("unzipFilePath"), entries.get("csvFileName"));

        List<WebsiteRank> websiteRankList = new ArrayList<>();

        try (LineNumberReader reader = new LineNumberReader(new FileReader(csvFile))) {
            String line;
            while (true) {
                line = reader.readLine();
                // end of file will throw NPE
                Matcher matcher = recordPattern.matcher(line);
                if (!matcher.find()) {
                    LOG.info("{} is not valid", line);
                    continue;
                }

                Long rank = Long.valueOf(matcher.group("rank"));
                String url = matcher.group("url");
                WebsiteRank websiteRank = new WebsiteRank(rank, url);
                websiteRankList.add(websiteRank);

                if (websiteRankList.size() % batchSize == 0) {
                    websiteRankService.batchAddWebsiteRank(websiteRankList);
                    LOG.info("Import {} records into database!", websiteRankList.size());
                    websiteRankList.clear();
                }
            }
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                if (websiteRankList.size() > 0) {
                    websiteRankService.batchAddWebsiteRank(websiteRankList);
                    LOG.info("Import {} records into database!", websiteRankList.size());
                    websiteRankList.clear();
                    LOG.info("End of the file...");
                }
            } else {
                LOG.error("Fail to read the csv file from {}", csvFile.getPath());
                throw new RuntimeException(e);
            }
        }
    }

}
