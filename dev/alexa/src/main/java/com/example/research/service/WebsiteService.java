package com.example.research.service;

import com.example.research.model.Website;

public interface WebsiteService {

    Website findById(Long id);
    Website upsert(Website website);
    void delete(Long id);

}
