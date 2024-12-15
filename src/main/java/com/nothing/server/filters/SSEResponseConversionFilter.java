package com.nothing.server.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SSEResponseConversionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse resp = ((HttpServletResponse) response);
		log.info("inside doFilter :: before");
		
		
		((HttpServletRequest) request).getHeaderNames().asIterator().forEachRemaining(log::info);
		
		
		log.info("Accept:"+((HttpServletRequest) request).getHeader("accept"));
		log.info("Accept-Encoding"+((HttpServletRequest) request).getHeader("accept"));




//		  resp.setHeader("Content-Type", "text/plain");
//		  response = resp;
		if (resp.getStatus() == 200) {
			log.info("200 response code received");

			log.info("Content-type:" + resp.getContentType());
			
			
			if(resp.getContentType() != null) {
				
				if(resp.getContentType().contains("text/event-stream")) {
					log.info("streaming object content type defined by writer");
				}else {
					log.info("server has not responded");

				}
			}
			
		

		}

		chain.doFilter(request, response);

	}

}
