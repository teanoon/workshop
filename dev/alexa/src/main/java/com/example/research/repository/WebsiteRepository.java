package com.example.research.repository;

import com.example.research.model.Website;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends CrudRepository<Website, Long> {

    Website findByDomain(String domain);

}
