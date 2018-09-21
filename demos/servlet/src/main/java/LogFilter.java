import com.sun.net.httpserver.HttpExchange;

import javax.servlet.*;
import java.io.IOException;


/**
 * Created by Air on 2018/4/10.
 */
public class LogFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        String param = config.getInitParameter("param");
        System.out.println("初始化参数"+param);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 输出站点名称
        System.out.println("filter生成");
        // 把请求传回过滤链
        filterChain.doFilter(servletRequest,servletResponse);
    }


    public void destroy(){
        System.out.println("filter注销");
    }
    public String description() {
        return null;
    }
}
