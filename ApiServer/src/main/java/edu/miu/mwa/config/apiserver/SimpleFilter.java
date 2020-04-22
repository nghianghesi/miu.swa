package edu.miu.mwa.config.apiserver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class SimpleFilter extends ZuulFilter {
	@Override
	public String filterType() {
	return "pre";
	}
	@Override
	public int filterOrder() {
	return 1;
	}
	@Override
	public boolean shouldFilter() {
	return true;
	}
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println(request.getMethod() + " request to " + request.getRequestURL().toString());
		return null;
	}
}
