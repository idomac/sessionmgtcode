package com.quanix.app.integration.core;

import org.apache.shiro.web.util.SavedRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : lihaoquan
 *
 * 客户端ClientSavedRequest
 *
 * 用于sessionmgt-app*模块当访问一些需要登录的请求时，自动把请求保存下来，
 * 然后重定向到sessionmgt-server模块登录；登录成功后再重定向回来；
 * 因为SavedRequest不保存URL中的schema://domain:port部分；
 * 所以才需要扩展SavedRequest；
 * 使得ClientSavedRequest能保存schema://domain:port；这样才能从一个应用重定向另一个（要不然只能在一个应用内重定向）
 */
public class ClientSavedRequest extends SavedRequest{

    private String scheme;
    private String domain;
    private int port;
    private String contextPath;
    private String backUrl;


    public ClientSavedRequest(HttpServletRequest request,String backUrl) {
        super(request);
        this.scheme = request.getScheme();
        this.domain = request.getServerName();
        this.port = request.getServerPort();
        this.backUrl = backUrl;
        this.contextPath = request.getContextPath();
    }

    public String getScheme() {
        return scheme;
    }

    public String getDomain() {
        return domain;
    }

    public int getPort() {
        return port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getBackUrl() {
        return backUrl;
    }

    /**
     * 1、如果从外部传入了successUrl（登录成功之后重定向的地址），
     *    且以http://或https://开头那么直接返回（相应的拦截器直接重定向到它即可）；
     * 2、如果successUrl有值但没有上下文，拼上上下文；
     * 3、否则，如果successUrl有值，直接赋值给requestUrl即可；否则，如果successUrl没值，那么requestUrl就是当前请求的地址；
     * 5、拼上url前边的schema，如http或https；
     * 6、拼上域名；
     * 7、拼上重定向到的地址（带上下文）；
     * 8、如果successUrl没值，且有查询参数，拼上；
     * 9返回该地址，相应的拦截器直接重定向到它即可。
     * @return
     */
    public String getRequestUrl() {
        String requestURI = getRequestURI();
        if(backUrl != null) {//1
            if(backUrl.toLowerCase().startsWith("http://") || backUrl.toLowerCase().startsWith("https://")) {
                return backUrl;
            } else if(!backUrl.startsWith(contextPath)) {//2
                requestURI = contextPath + backUrl;
            } else {//3
                requestURI = backUrl;
            }
        }

        StringBuilder requestUrl = new StringBuilder(scheme);//4
        requestUrl.append("://");
        requestUrl.append(domain);//5
        //6
        if("http".equalsIgnoreCase(scheme) && port != 80) {
            requestUrl.append(":").append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(scheme) && port != 443) {
            requestUrl.append(":").append(String.valueOf(port));
        }
        //7
        requestUrl.append(requestURI);
        //8
        if (backUrl == null && getQueryString() != null) {
            requestUrl.append("?").append(getQueryString());
        }
        return requestUrl.toString();
    }
}
