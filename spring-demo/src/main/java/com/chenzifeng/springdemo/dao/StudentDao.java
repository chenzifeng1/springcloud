package com.chenzifeng.springdemo.dao;

import com.chenzifeng.springdemo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student,Integer> {

}
