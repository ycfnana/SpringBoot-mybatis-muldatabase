package com.example.demo.dao.mapping;

import com.example.demo.entity.test;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {


    public void saveUser(test test);
}
