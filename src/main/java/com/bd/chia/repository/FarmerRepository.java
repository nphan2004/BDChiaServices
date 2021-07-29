package com.bd.chia.repository;

import org.springframework.data.repository.CrudRepository;

import com.bd.chia.jpa.Farmer;

public interface FarmerRepository extends CrudRepository<Farmer, String> {

}
