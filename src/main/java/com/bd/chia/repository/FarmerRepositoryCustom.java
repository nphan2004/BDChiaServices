package com.bd.chia.repository;

import java.util.List;

import com.bd.chia.repository.FarmerRepositoryImpl.FarmerStats;

public interface FarmerRepositoryCustom {
	List<FarmerStats> getLeaderBoard();
	Long totalPoints();
}
