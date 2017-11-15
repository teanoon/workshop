package com.example.research.service.impl;

import com.example.research.configuration.CoreConfiguration;
import com.example.research.model.Website;
import com.example.research.service.WebsiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CoreConfiguration.class)
public class WebsiteServiceImplTest {

    private static final String DOMAIN = "domain.com";

    @Autowired
    private WebsiteService service;

    @Test
    @Transactional
    @Rollback(value = false)
    public void addWebsite() {
        Website website = new Website();
        website.setDomain(DOMAIN);
        website.setDescription("...");
        Website added = service.upsert(website);
        assertEquals(DOMAIN, added.getDomain());
    }


    @Test
    public void upsertWithRanks() {
        Website website = new Website();
        website.setDomain(DOMAIN);
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