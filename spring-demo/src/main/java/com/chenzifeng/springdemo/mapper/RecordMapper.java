package com.chenzifeng.springdemo.mapper;

import com.chenzifeng.springdemo.model.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordMapper extends CrudRepository<Record,Integer> {
}
