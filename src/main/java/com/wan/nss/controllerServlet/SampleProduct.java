package com.wan.nss.controllerServlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.wan.nss.biz.product.ProductDAO;
import com.wan.nss.biz.product.ProductVO;

@WebListener
public class SampleProduct implements ServletContextListener {

    public SampleProduct() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	
    	// ServletContext application = sce.getServletContext(); // application scope
    	ProductVO pvo = new ProductVO();
    	ProductDAO pdao = new ProductDAO();
    	pvo.setpSearchCondition("all");
    	// 크롤링한 데이터 set
    	System.out.println("pdao.selectAll(pvo).size(): "+pdao.selectAll(pvo).size());
    	if(pdao.selectAll(pvo).size() < 48) { // 전체 데이터 개수 < 48
    		com.wan.nss.common.Crawling.sample();
    	}
    	
    }
	
}
