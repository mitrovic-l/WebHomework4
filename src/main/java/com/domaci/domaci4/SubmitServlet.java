package com.domaci.domaci4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name="submitServlet", value = "/submit")
public class SubmitServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(request, response);
        response.setContentType("text/html");
        CopyOnWriteArrayList<String> users = (CopyOnWriteArrayList<String>) getServletContext().getAttribute("users");
        ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> menu = (ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>>) getServletContext().getAttribute("order");
        users.add(request.getSession().getId());

        menu.get("monday").putIfAbsent(request.getParameter("monday"), 0);
        menu.get("tuesday").putIfAbsent(request.getParameter("tuesday"), 0);
        menu.get("wednesday").putIfAbsent(request.getParameter("wednesday"), 0);
        menu.get("thursday").putIfAbsent(request.getParameter("thursday"), 0);
        menu.get("friday").putIfAbsent(request.getParameter("friday"), 0);

        menu.get("monday").put(request.getParameter("monday"), menu.get("monday").get(request.getParameter("monday")) + 1);
        menu.get("tuesday").put(request.getParameter("tuesday"), menu.get("tuesday").get(request.getParameter("tuesday")) + 1);
        menu.get("wednesday").put(request.getParameter("wednesday"), menu.get("wednesday").get(request.getParameter("wednesday")) + 1);
        menu.get("thursday").put(request.getParameter("thursday"), menu.get("thursday").get(request.getParameter("thursday")) + 1);
        menu.get("friday").put(request.getParameter("friday"), menu.get("friday").get(request.getParameter("friday")) + 1);

        System.out.println("ORDER: " + menu);

        //monday
        //tuesday
        //wednesday
        //thursday
        //friday
        request.getSession().setAttribute("monday", request.getParameter("monday"));
        request.getSession().setAttribute("tuesday", request.getParameter("tuesday"));
        request.getSession().setAttribute("wednesday", request.getParameter("wednesday"));
        request.getSession().setAttribute("thursday", request.getParameter("thursday"));
        request.getSession().setAttribute("friday", request.getParameter("friday"));

        PrintWriter out = response.getWriter();
        out.println("<html> <style>\n" +
                "table, th, td {\n" +
                "  border:1px solid blue;\n" +
                "}\n" +
                "</style><body>");
        out.println("<h1>Your order has been confirmed!</h1>");

        out.println("<table style=\" width:100% \" ");

        out.println("<tr>");
        out.println("<th> Monday </th>");
        out.println("<th> Tuesday </th>");
        out.println("<th> Wednesday </th>");
        out.println("<th> Thursday </th>");
        out.println("<th> Friday </th>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>" + request.getParameter("monday"));
        out.println("<td>" + request.getParameter("tuesday"));
        out.println("<td>" + request.getParameter("wednesday"));
        out.println("<td>" + request.getParameter("thursday"));
        out.println("<td>" + request.getParameter("friday"));
        out.println("</tr>");

        out.println("</table>");

        out.println("</body></html>");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
