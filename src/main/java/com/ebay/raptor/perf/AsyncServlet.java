package com.ebay.raptor.perf;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/async"}, asyncSupported=true)
public class AsyncServlet extends HttpServlet {

	private static final long serialVersionUID = 8458647350928584967L;
	private static final Queue queue = new ConcurrentLinkedQueue();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ){
		final AsyncContext ac = request.startAsync();
		ac.setTimeout(1 * 60 * 1000);
		
		queue.add(ac);
		ac.addListener( new AsyncListener() {
			
			public void onTimeout(AsyncEvent event) throws IOException {
				System.out.println("onTimeout");
				queue.remove(ac);
			}
			
			public void onStartAsync(AsyncEvent event) throws IOException {
				System.out.println("onStart");
				queue.add(ac);
			}
			
			public void onError(AsyncEvent event) throws IOException {
				System.out.println("onError");
			}
			
			public void onComplete(AsyncEvent event) throws IOException {
				System.out.println("onComplete");
				queue.remove(ac);
			}
		});
	}
	
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ){
		doGet(request, response);
	}

}
