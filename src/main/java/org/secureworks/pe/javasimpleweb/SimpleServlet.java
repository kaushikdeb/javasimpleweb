package org.secureworks.pe.javasimpleweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("serial")
public class SimpleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    	
    	String text = request.getParameter("text");
    	String hashingAlgorithm = request.getParameter("hashingAlgorithm");
    	String restURL = "http://jerseysimplerest/myapp/myresource/" + hashingAlgorithm + 
    			"/" + text;
    	
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>PE Java Simple Web</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>PE Jersey Simple Rest</h1>");
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(restURL);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        // The underlying HTTP connection is still held by the response object
        // to allow the response content to be streamed directly from the network socket.
        // In order to ensure correct deallocation of system resources
        // the user MUST call CloseableHttpResponse#close() from a finally clause.
        // Please note that if response content is not fully consumed the underlying
        // connection cannot be safely re-used and will be shut down and discarded
        // by the connection manager. 
        try {
            out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            out.println(EntityUtils.toString(entity1));
            
            EntityUtils.consume(entity1);
        } finally {
            response1.close();
        }


        out.println("</body>");
        out.println("</html>");
        
    }
    
    
}