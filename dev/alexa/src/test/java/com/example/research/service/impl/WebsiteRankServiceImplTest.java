package com.example.research.service.impl;

import com.example.research.configuration.CoreConfiguration;
import com.example.research.service.WebsiteRankService;
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
public class WebsiteRankServiceImplTest {

    @Autowired
    private WebsiteRankService service;

    @Test
    @Rollback(value = false)
    public void addWebSiteRank() {


    }

}