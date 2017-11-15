package com.example.research.service.impl;

import com.example.research.model.Rank;
import com.example.research.repository.RankRepository;
import com.example.research.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService{

    @Autowired
    private RankRepository rankRepository;

    @Override
    public Rank findRankById(Long id) {
        return rankRepository.findOne(id);
    }

    @Override
    public boolean addRank(Rank rank) {
        rankRepository.save(rank);
        return true;
    }

}
