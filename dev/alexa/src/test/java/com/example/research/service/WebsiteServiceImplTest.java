package com.example.research.service;

import com.example.research.configuration.CoreConfiguration;
import com.example.research.model.Website;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CoreConfiguration.class)
public class WebsiteServiceImplTest {

    private static final String DOMAIN = "domain.com";

    @Autowired
    private WebsiteService service;

    @Test
    public void upsertWithRanks() {
        Website website = new Website();
        website.setDomain(DOMAIN);
        website.addRank(ThreadLocalRandom.current().nextInt(100_000));
        service.upsert(website);

        assertRanks(website);
    }

    @Transactional
    public void assertRanks(Website website) {
        Website actual = service.findByDomain(website.getDomain());
        assertNotSame(website, actual);
        assertFalse(actual.getRanks().isEmpty());
    }


}
