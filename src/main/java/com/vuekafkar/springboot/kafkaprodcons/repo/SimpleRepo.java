package com.vuekafkar.springboot.kafkaprodcons.repo;

import com.vuekafkar.springboot.kafkaprodcons.SimpleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleRepo extends JpaRepository<SimpleModel, Integer> {
}
