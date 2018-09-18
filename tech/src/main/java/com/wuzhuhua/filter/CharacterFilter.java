package com.wuzhuhua.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 部分字符过滤
 */
public class CharacterFilter implements Filter {

    private String unCheckURLS;

    @Override
    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");
        if (!request.getRequestURI().matches(unCheckURLS)) {
            Enumeration<String> enumerator = req.getParameterNames();
            Map<?, ?> pMap = req.getParameterMap();
            while (enumerator.hasMoreElements()) {
                String paraName = enumerator.nextElement();
                String[] value = (String[]) pMap.get(paraName);
                if (value.length == 0) {
                    continue;
                }
                for (int i = 0; i < value.length; i++) {
                    value[i] = filterString(value[i]);
                }
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
        StringBuffer result = new StringBuffer();
        for (StringTokenizer token = new StringTokenizer(filterconfig.getInitParameter("unCheckURLS"), "\n"); token
                .hasMoreTokens(); result.append(token.nextToken().trim()));
        unCheckURLS = result.toString();
    }

    @Override
    public void destroy() {
    }

    /**
     * 过滤字符集
     * 
     * @param inputStr
     *            需要过滤的字符串
     * @return 过滤后的字符串
     */
    private String filterString(String inputStr) {
    
       if(StringUtils.hasText(inputStr)){
    	   return inputStr;
       }
        inputStr = convertHtmlZy(inputStr);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inputStr.length(); i++) {
            char aChar = inputStr.charAt(i);
            switch (aChar) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
               /* case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;*/
                case '&':
                sb.append('＆');// 全角
                break;
               /* case '[':
                    sb.append('【');// 全角
                    break;
                case ']':
                    sb.append('】');// 全角
                    break;*/
                // case '\\':
                // sb.append('＼');// 全角斜线
                // break;
                /*case '#':
                    sb.append('＃');// 全角井号
                    break;
                case ';':
                    sb.append('；');// ;转化为；
                    break;*/
                default:
                    sb.append(aChar);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 将转义字符转换成相应的广角字符
     * 
     * @param inputStr
     * @return
     */
    public String convertHtmlZy(String inputStr) {
        if (!StringUtils.hasText(inputStr)) {
            String temp = inputStr.toLowerCase();
            if (-1 != temp.indexOf("&amp")) {
                temp = temp.replaceAll("&amp", "＆");
            } else if (-1 != temp.indexOf("&gt")) {
                temp = temp.replaceAll("&gt", "＞");
            } else if (-1 != temp.indexOf("&lt")) {
                temp = temp.replaceAll("&lt", "＜");
            } else if (-1 != temp.indexOf("&quot")) {
                temp = temp.replaceAll("&quot", "“");
            } else if (-1 != temp.indexOf("&apos")) {
                temp = temp.replaceAll("&apos", "‘");
            } else {
                temp = inputStr;
            }
            return temp;

        }
        return inputStr;

    }
}