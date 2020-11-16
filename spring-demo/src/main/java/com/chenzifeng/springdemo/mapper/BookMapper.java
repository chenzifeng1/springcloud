package com.chenzifeng.springdemo.mapper;


import com.chenzifeng.springdemo.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper extends CrudRepository<Book,Integer> {

    Iterable<Book> findBooksByBookName(String bookName);
    Iterable<Book> findBooksByAuthor(String author);
}
