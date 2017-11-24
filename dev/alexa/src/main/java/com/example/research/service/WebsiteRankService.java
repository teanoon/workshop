package com.example.research.service;

import com.example.research.model.WebsiteRank;

import java.util.List;

public interface WebsiteRankService {

    void addWebsiteRank(WebsiteRank websiteRank);

    void batchAddWebsiteRank(List<WebsiteRank> websiteRanks);

}
