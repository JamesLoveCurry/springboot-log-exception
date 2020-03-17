package com.lyj.blog.controller;

import com.lyj.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: springboot-lingxiblog
 * @Date: 2020/3/16 22:09
 * @Author: 李玉杰
 * @Description:
 */
@Controller
public class LoginController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id,@PathVariable String name){
//        int t=5/0;
//        String blog= null;
//        if (blog==null){
//            throw new NotFoundException("页面找不到");
//        }
        System.out.println("--------index---------");
        return "index";
    }
}
