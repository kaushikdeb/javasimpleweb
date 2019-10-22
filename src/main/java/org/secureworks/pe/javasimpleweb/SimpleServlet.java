package org.secureworks.pe.javasimpleweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

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

        private String TOP_HTML_TEXT = "<!DOCTYPE html>\r\n" +
                        "<html lang=\"en\">\r\n" +
                        "<head>\r\n" +
                        "  <title>PE Java Simple Web</title>\r\n" +
                        "  <meta charset=\"utf-8\">\r\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" +
                        "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\r\n" +
                        "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" +
                        "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n" +
                        "</head>\r\n" +
                        "<body>\r\n" +
                        "\r\n" +
                        "<div class=\"container\">" +
                        "<h2>Response from backend Jersey REST API</h2>";

	
    private String BOTTOM_HTML_TEXT =
            "<button onclick=\"history.back(-1)\">Go Back</button>" +
            "</div>\r\n" +
                        "</body>\r\n" +
                        "</html>";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {

        String text = request.getParameter("text");
        text = URLEncoder.encode(text, "UTF-8");
        String hashingAlgorithm = request.getParameter("hashingAlgorithm");
        String restURL = "http://localhost:8080/myapp/" + hashingAlgorithm +
                        "/" + text;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(TOP_HTML_TEXT);

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


        out.println(BOTTOM_HTML_TEXT);

    }


}
