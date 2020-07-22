package com.github.jiangxch.hasakei.config.manager;

import com.github.jiangxch.hasakei.common.exceptions.AuthException;
import com.github.jiangxch.hasakei.config.dao.entity.Users;
import com.github.jiangxch.hasakei.config.service.UsersServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于认证和鉴定权限
 */
@Component
public class AuthManager {
    
    @Autowired
    private JwtTokenManager tokenManager;
    @Autowired
    private UsersServiceImpl usersService;
    
    public AuthInfo login(HttpServletRequest req) throws AuthException {
        String token = resolveToken(req);
        try {
            tokenManager.validateToken(token);
        } catch (ExpiredJwtException e) {
            throw new AuthException("token expired!");
        } catch (Exception e) {
            throw new AuthException("token invalid!");
        }
        String username = tokenManager.getUsernameFromJwt(token);
        Users user = usersService.getByUsername(username);
        if (user == null) {
            String msg = String.format("can't find out Users by username=%s", username);
            throw new AuthException(msg);
        }

        AuthInfo authInfo = new AuthInfo();
        authInfo.setUsername(username);
        authInfo.setToken(token);
        return authInfo;
    }
    

    private String resolveToken(HttpServletRequest request) {
        String jwt = request.getHeader(tokenManager.HEADER);
        if (jwt == null || jwt.length() == 0) {
            String userName = getUsernameFromRequest(request);
            String password = getPasswordFromRequest(request);
            jwt = resolveTokenFromUser(userName, password);
        }
        return jwt;
    }

    private String getUsernameFromRequest(HttpServletRequest request) {
        String username = request.getParameter("username");
        if (username == null || username.length() == 0) {
            username = request.getAttribute("username").toString();
        }
        return username;
    }

    private String getPasswordFromRequest(HttpServletRequest request) {
        String password = request.getParameter("password");
        if (password == null || password.length() == 0) {
            password = request.getAttribute("username").toString();
        }
        return password;
    }
    
    private String resolveTokenFromUser(String userName, String rawPassword) throws AuthException {
        Users user = usersService.getByUsernameAndPassword(userName, rawPassword);
        if (user == null) {
            String msg = String.format("can't find matched user,username=%s", userName);
            throw new AuthException(msg);
        }
        return tokenManager.createToken(userName);
    }
}