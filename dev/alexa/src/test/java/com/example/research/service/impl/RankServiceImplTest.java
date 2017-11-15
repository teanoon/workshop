package com.example.research.service.impl;

import com.example.research.configuration.CoreConfiguration;
import com.example.research.model.Rank;
import com.example.research.model.Website;
import com.example.research.service.RankService;
import com.example.research.service.WebsiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreConfiguration.class)
@Transactional
public class RankServiceImplTest {

    @Autowired
    private RankService rankService;

    @Autowired
    private WebsiteService websiteService;

    @Test
    @Rollback(value = false)
    public void addRank() {
        Website website = new Website();
        website.setDomain("domain");
        website.setKeywords("keywords");
        website.setDescription("some stuff...");

        Rank rank = new Rank();
        rank.setValue(1000);
        rank.setDateBeforeSave();
        rank.setWebsite(website);

        website.addRank(rank);

        rankService.addRank(rank);
    }


}