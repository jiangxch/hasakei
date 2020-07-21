package com.github.jiangxch.hasakei.config.controller;

import com.github.jiangxch.hasakei.common.model.Result;
import com.github.jiangxch.hasakei.config.manager.AuthInfo;
import com.github.jiangxch.hasakei.config.manager.AuthManager;
import com.github.jiangxch.hasakei.config.manager.JwtTokenManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * @author: jiangxch
 * @date: 2020/7/6 11:42
 */
@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private AuthManager authManager;
    @Autowired
    private JwtTokenManager tokenManager;

    @ApiOperation(value = "登录", tags = "用户管理")
    @PostMapping("login")
    public Result login(@RequestParam @NotBlank String username,
                        @RequestParam @NotBlank String password,
            HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        AuthInfo authInfo = authManager.login(request);
        response.setHeader(tokenManager.HEADER,authInfo.getToken());
        return Result.newSuccess();
    }
}
