package com.bd.chia.repository;

import java.util.List;

import com.bd.chia.jpa.Partial;

public interface PartialRepositoryCustom {
	List<Partial> findByLauncherId(String launcherId);
}
