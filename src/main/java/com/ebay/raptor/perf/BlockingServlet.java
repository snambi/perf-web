package com.ebay.raptor.perf;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="testServlet", urlPatterns={"/blocking"},
initParams={ @WebInitParam(name="param1", value="value1") } )
public class BlockingServlet extends HttpServlet{

	private static final long serialVersionUID = 9103024280798904724L;

	protected void doGet(HttpServletRequest request, 
    						HttpServletResponse response) throws ServletException, IOException {
    	
        
        String simpleParam = getServletConfig().getInitParameter("simpleParam");
        String sleepTime = (String) request.getAttribute("sleep");
        int sleepMillis = Util.convertToMillis(sleepTime);
        
        try {
			Thread.currentThread().sleep(sleepMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        PrintWriter out = response.getWriter();
        out.println("<html><head></head><body><div>slept for " + sleepMillis + " milliseconds</div></body></html>");
        out.close();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }
}
