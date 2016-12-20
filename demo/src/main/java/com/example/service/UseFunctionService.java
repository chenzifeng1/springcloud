package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 陈子枫 on 2016/12/19.
 */
@Service
public class UseFunctionService {
    @Autowired
    FunctionService functionService;

    public String sayHello(String word){
        return  functionService.sayHello(word);
    }
}
