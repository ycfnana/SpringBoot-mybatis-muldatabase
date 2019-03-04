package com.example.demo.Controller;

import com.example.demo.dao.mapping.UserMapper;
import com.example.demo.entity.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/")
@ResponseBody
public class testController {


    @Autowired
    UserMapper userMapper;

    @GetMapping("/iii")
    public boolean save(){
        test test=new test();
        test.setUserName("sda");
        userMapper.saveUser(test);
        return true;
    }

}
