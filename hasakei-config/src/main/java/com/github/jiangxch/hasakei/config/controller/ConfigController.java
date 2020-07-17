package com.github.jiangxch.hasakei.config.controller;

import com.github.jiangxch.hasakei.api.exception.HsakeiException;
import com.github.jiangxch.hasakei.config.arg.ConfigArg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: jiangxch
 * @date: 2020/7/6 11:42
 */
@RequestMapping("config")
@RestController
public class ConfigController {


    @PostMapping
    public Boolean publishConfig(HttpServletRequest request, HttpServletResponse response,
                                 ConfigArg configArg) throws HsakeiException {

        return true;
    }

    @GetMapping
    public String getConfig()  {


        return "true";
    }

}