package com.chenzifeng.springdemo.mapper;

import com.chenzifeng.springdemo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends CrudRepository<Student,Integer> {

}
