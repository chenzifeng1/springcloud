package com.chenzifeng.springdemo.dao;


import com.chenzifeng.springdemo.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends CrudRepository<Book,Integer> {

    Iterable<Book> findBooksByBookName(String bookName);
    Iterable<Book> findBooksByAuthor(String author);
}
