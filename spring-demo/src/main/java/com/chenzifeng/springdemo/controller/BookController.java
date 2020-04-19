package com.chenzifeng.springdemo.controller;

import com.chenzifeng.springdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(path = "/getBookByName",method = RequestMethod.GET)
    public Object getBookByName(String name){
        return bookService.findBookByName(name);
    }

    @RequestMapping(path = "/addBook",method = RequestMethod.POST)
    public Object addBook(String name,String author,int typeId,int num){
        return bookService.addOne(typeId,num,name,author);
    }

}
