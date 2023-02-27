package com.wan.nss.controllerServlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.wan.nss.biz.product.ProductDAO;

/**
 *
 */
@WebListener
public class SampleProduct implements ServletContextListener {

    public SampleProduct() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	
    	// ServletContext application = sce.getServletContext(); // application scope
    	ProductDAO pdao = new ProductDAO();
    	
    	// 크롤링한 데이터 set
//    	System.out.println("pdao.selectAll(null).size(): "+pdao.selectAll(null).size());
//    	if(pdao.selectAll(null).size() < 48) { // 전체 데이터 개수 < 48
//    		com.wan.nss.common.Crawling.sample();
//    	}
    	
    	System.out.println("	로그: 크롤링 미완성... T_T");
    	
    }
	
}
