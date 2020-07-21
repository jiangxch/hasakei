package com.github.jiangxch.hasakei.config.filter;

import com.github.jiangxch.hasakei.common.exceptions.AuthException;
import com.github.jiangxch.hasakei.common.util.ExceptionUtil;
import com.github.jiangxch.hasakei.config.manager.AuthInfo;
import com.github.jiangxch.hasakei.config.manager.AuthManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.jiangxch.hasakei.config.manager.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: jiangxch
 * @date: 2020/7/20 下午9:23
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthFilter extends OncePerRequestFilter implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    /**
     * 项目内的handlerMapping，不包括集成jar中的handlerMapping
     */
    private HandlerMapping handlerMapping;

    @Autowired
    private AuthManager authManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HandlerExecutionChain mappedHandler = null;
        try {
            mappedHandler = handlerMapping.getHandler(request);
            // 拿到mappedHandler就走鉴权逻辑,没有就放行
            if (mappedHandler != null && !request.getRequestURI().equals("/user/login")) {
                if (mappedHandler.getHandler() instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) mappedHandler.getHandler();
                    Class<?> controllerType = handlerMethod.getBeanType();
                    String packageName = controllerType.getPackage().getName();
                    //controller属于本应用hasakei的controller,防止其他controller干扰(swagger中的)
                    if (packageName!=null&&packageName.startsWith("com.github.jiangxch.hasakei")) {
                        // 认证
                        AuthInfo authInfo = authManager.login(request);
                        // 设置全局认证信息
                        RequestContextHolder.setContext(authInfo);
                    }
                }
            }

            // 鉴定权限
        } catch (AuthException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, ExceptionUtil.getAllExceptionMsg(e));
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        filterChain.doFilter(request, response);
    }


    /**
     * 获取IOC容器，目的是为了拿到Spring MVC的handlerMapping bean
     *
     * @param applicationContext
     * @throws BeansException
     * @see org.springframework.web.servlet.DispatcherServlet#initHandlerMappings
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.handlerMapping = applicationContext.getBean("requestMappingHandlerMapping", HandlerMapping.class);
    }
}
