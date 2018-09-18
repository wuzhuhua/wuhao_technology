package com.wuzhuhua.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.redswallow.filter.RedSwallowFilter;

/**
 * @ClassName: RedSwallowFilterNew
 * @Description: XSS过滤器
 * @author 
 */
public class RedSwallowFilterNew extends RedSwallowFilter
{
    
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {
        super.init(filterConfig);
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String url = httpRequest.getRequestURI();
        if (url.indexOf("/ueditor/jsp/")!=-1)
        {
            System.out.println("hi i come in don't be warry!");
            chain.doFilter(request, response);
        }
        else
        {
            super.doFilter(request, response, chain);
        }
    }
    
    @Override
    public void destroy()
    {
        super.destroy();
    }
    
}