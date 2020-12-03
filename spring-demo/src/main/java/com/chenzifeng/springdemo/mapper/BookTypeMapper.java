package com.chenzifeng.springdemo.mapper;

import com.chenzifeng.springdemo.model.BookType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeMapper extends CrudRepository<BookType, Integer> {

}
