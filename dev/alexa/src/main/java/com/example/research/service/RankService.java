package com.example.research.service;

import com.example.research.model.Rank;

public interface RankService {

    Rank findRankById(Long id);

    boolean addRank(Rank rank);

}
