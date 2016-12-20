package com.example.service;

import org.springframework.stereotype.Service;

/**
 * Created by 陈子枫 on 2016/12/19.
 */
@Service
public class FunctionService {
    public String sayHello(String word){
        return "Hello," + word+"!";
    }
}
