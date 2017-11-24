package com.example.research.service.impl;

import com.example.research.model.Website;
import com.example.research.repository.WebsiteRepository;
import com.example.research.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private WebsiteRepository repo;

    @Override
    public Website findById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Website findByDomain(String domain) {
        return repo.findByDomain(domain);
    }

    @Override
    public Website upsert(Website website) {
        Website old = repo.findByDomain(website.getDomain());
        if (old != null) {
            website.setId(old.getId());
        }
        return repo.save(website);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }

}