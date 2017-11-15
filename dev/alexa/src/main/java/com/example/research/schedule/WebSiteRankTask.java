package com.example.research.schedule;

import com.example.research.properties.CustomProperties;
import com.example.research.service.WebsiteRankService;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Component
public class WebSiteRankTask {

    @Autowired
    private WebsiteRankService rankImportService;

    @Autowired
    private CustomProperties properties;

    @Autowired
    private WebsiteRankImporter websiteRankImporter;

    private static final Logger LOG = LoggerFactory.getLogger(WebSiteRankTask.class);

    @Scheduled(fixedRate = 10000000)
    public void downZipFileAndStore() {
        Map<String, String> entries = properties.getEntries();
        String downloadFilePath = entries.get("sourceDownloadPath");
        String unzipFilePath = entries.get("unzipFilePath");
        String sourceZipPath = entries.get("sourceZipPath");

        try {
            URL url = new URL(sourceZipPath);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            try (InputStream stream = con.getInputStream()) {
                File downloadFile = new File(downloadFilePath);
                if (downloadFile.exists()) {
                    downloadFile.delete();
                }

                Files.copy(stream, Paths.get(downloadFilePath));
                LOG.info("Download zip file from Url {} successfully!", url.getPath());
            }

            unzipFile(downloadFilePath, unzipFilePath);

            LOG.info("Loading data into mysql now...");
            websiteRankImporter.loadData();
        } catch (IOException e) {
            LOG.error("Error occurs when connect with url {}", "");
            throw new RuntimeException(e);
        }

    }

    private void unzipFile(String srcFile, String destFile) {
        try {
            ZipFile zipFile = new ZipFile(srcFile);
            zipFile.extractAll(destFile);
            LOG.info("Unzip file from path {} successfully", srcFile);
        } catch (ZipException e) {
            LOG.error("Error occurs when unzip file {}", srcFile, e);
            throw new RuntimeException(e);
        }
    }

}
