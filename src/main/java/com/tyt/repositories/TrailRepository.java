package com.tyt.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tyt.dto.Trail;

public interface TrailRepository extends MongoRepository<Trail, String> {

	public Trail findByTitle(String title);
}
