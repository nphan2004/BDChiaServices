package com.bd.chia.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.bd.chia.jpa.Partial;

@EnableScan
public interface PartialRepository extends CrudRepository<Partial, Partial.PrimaryKey>, PartialRepositoryCustom {

}
