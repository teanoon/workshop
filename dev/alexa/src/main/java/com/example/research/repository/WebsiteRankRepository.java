package com.example.research.repository;

import com.example.research.model.WebsiteRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRankRepository extends JpaRepository<
        WebsiteRank, Long> {

}
