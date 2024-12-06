package com.example.demo.repository;

import com.example.demo.model.LinkModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

@Repository
public interface LinkRepository extends KeyValueRepository<LinkModel,Long> {
}
