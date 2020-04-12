package com.chenzifeng.springdemo.dao;

import com.chenzifeng.springdemo.model.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordDao extends CrudRepository<Record,Integer> {
}
