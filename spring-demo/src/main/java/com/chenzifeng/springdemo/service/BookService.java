package com.chenzifeng.springdemo.service;

import com.chenzifeng.springdemo.mapper.BookMapper;
import com.chenzifeng.springdemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookMapper bookDao;



    public Book addOne(int typeId,int restNum,String bookName,String author){

        Book book = new Book(bookName,author,typeId,restNum);
        return  bookDao.save(book);
    }

    public Iterable<Book> findBookByName(String name){
        return bookDao.findBooksByBookName(name);
    }

}
