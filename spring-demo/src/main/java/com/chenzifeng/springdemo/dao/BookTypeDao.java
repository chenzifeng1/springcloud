package com.chenzifeng.springdemo.dao;

import com.chenzifeng.springdemo.model.Book;
import com.chenzifeng.springdemo.model.BookType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeDao extends CrudRepository<BookType,Integer> {

}
