package com.by.servlet;

import cn.hutool.core.date.StopWatch;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*@WebFilter("/*")*/
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        filterChain.doFilter(servletRequest, servletResponse);
        stopWatch.stop();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String url = httpServletRequest.getRequestURI();
        log.debug("{} 请求耗时：{} ms", url, stopWatch.getTotalTimeMillis());
    }
}
