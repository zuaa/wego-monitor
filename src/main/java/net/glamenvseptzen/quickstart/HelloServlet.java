package net.glamenvseptzen.quickstart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");

        Properties msg = new Properties();
        msg.load(getClass().getClassLoader().getResourceAsStream(
                "net/glamenvseptzen/quickstart/msg.properties"));

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println(MyGroovyUtil.greed("Servlet Programming"));
        out.println("<hr>");

        String caps[] = MyStringUtil.splitAndCapitalize(
                msg.getProperty("msg1"), " ");
        out.println("<ul>");
        for (String s : caps) {
            out.println("<li>" + s + "</li>");
        }
        out.println("</ul>");

        out.println("<hr>");

        caps = MyStringUtil.splitAndCapitalize(msg.getProperty("msg2"), " ");
        out.println("<ul>");
        for (String s : caps) {
            out.println("<li>" + s + "</li>");
        }
        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
