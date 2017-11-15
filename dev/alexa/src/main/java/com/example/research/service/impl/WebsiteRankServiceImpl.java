package com.example.research.service.impl;

import com.example.research.model.WebsiteRank;
import com.example.research.repository.WebsiteRankRepository;
import com.example.research.service.WebsiteRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteRankServiceImpl implements WebsiteRankService {

    @Autowired
    private WebsiteRankRepository websiteRankRepository;

    @Override
    public void addWebsiteRank(WebsiteRank websiteRank) {
        websiteRankRepository.save(websiteRank);
    }

    @Override
    public void batchAddWebsiteRank(List<WebsiteRank> websiteRanks) {
        websiteRankRepository.save(websiteRanks);
    }

}
