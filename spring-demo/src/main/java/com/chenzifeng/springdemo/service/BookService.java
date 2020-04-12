package com.chenzifeng.springdemo.service;

import com.chenzifeng.springdemo.dao.BookDao;
import com.chenzifeng.springdemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void addOne(Book book){

    }

}
