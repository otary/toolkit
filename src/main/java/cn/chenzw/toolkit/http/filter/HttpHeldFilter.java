package cn.chenzw.toolkit.http.filter;

import cn.chenzw.toolkit.http.HttpHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author chenzw
 */
public class HttpHeldFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpHolder.init((HttpServletRequest) request, (HttpServletResponse) response);
        try {
            chain.doFilter(request, response);
        } finally {
            HttpHolder.clear();
        }
    }
}
