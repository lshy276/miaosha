package com.lsy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 李帅豫 on 2020/4/22.
 */
@RestController
public class TestController {
    @RequestMapping("lsy")
    public String lsy(){
        return "李帅豫";
    }

    @RequestMapping("lxs")
    public String lxs(){
        return "李先生";
    }


}
