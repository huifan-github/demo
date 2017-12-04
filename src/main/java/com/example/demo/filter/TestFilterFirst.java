package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//配置过滤器(名称,过滤对象)
@WebFilter(filterName = "testFilter1", urlPatterns = "/*")
public class TestFilterFirst implements Filter {
    private final static Logger log = LoggerFactory.getLogger(TestFilterFirst.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("过滤器测试!");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

