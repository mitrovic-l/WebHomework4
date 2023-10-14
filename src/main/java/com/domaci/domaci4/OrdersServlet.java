package com.domaci.domaci4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "ordersServlet", value = "/ordered")
public class OrdersServlet extends HttpServlet {
    private String password = "";
    public void init(){
        initPassword();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> menu = (ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>>) getServletContext().getAttribute("order");

        out.println("<html> <style>\n" +
                "table, th, td {\n" +
                "  border:1px solid blue;\n" +
                "}\n" +
                "table{" +
                        "width: 100%"+
                "}\n"+
                "button{" +
                "background-color: #f44336;" +
                "}"+
                "</style><body>");
        if (request.getParameter("password") != null && request.getParameter("password").equals(password)){
            out.println("<h1> Ordered meals </h1>");
            out.println("<form method=\"post\" action=\"/ordered?lozinka=" + password + "\">");
            out.println("<button type=\"submit\">Clear</button>");
            out.println("</form>");

            out.println("<h2> Monday </h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> Meal </th>");
            out.println("<th> Number of orders </th>");
            out.println("</tr>");
            for (Map.Entry entry: menu.get("monday").entrySet()){
                out.println("<tr>");
                out.println("<td> " + entry.getKey() + " </td>");
                out.println("<td> " + entry.getValue() + " </td>");
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("<h2> Tuesday </h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> Meal </th>");
            out.println("<th> Number of orders </th>");
            out.println("</tr>");
            for (Map.Entry entry: menu.get("tuesday").entrySet()){
                out.println("<tr>");
                out.println("<td> " + entry.getKey() + " </td>");
                out.println("<td> " + entry.getValue() + " </td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<h2> Wednesday </h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> Meal </th>");
            out.println("<th> Number of orders </th>");
            out.println("</tr>");
            for (Map.Entry entry: menu.get("wednesday").entrySet()){
                out.println("<tr>");
                out.println("<td> " + entry.getKey() + " </td>");
                out.println("<td> " + entry.getValue() + " </td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<h2> Thursday </h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> Meal </th>");
            out.println("<th> Number of orders </th>");
            out.println("</tr>");
            for (Map.Entry entry: menu.get("thursday").entrySet()){
                out.println("<tr>");
                out.println("<td> " + entry.getKey() + " </td>");
                out.println("<td> " + entry.getValue() + " </td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<h2> Friday </h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> Meal </th>");
            out.println("<th> Number of orders </th>");
            out.println("</tr>");
            for (Map.Entry entry: menu.get("friday").entrySet()){
                out.println("<tr>");
                out.println("<td> " + entry.getKey() + " </td>");
                out.println("<td> " + entry.getValue() + " </td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } else {
            response.setStatus(403);
            out.println("<h1>403 UNAUTHORIZED </h1>");

        }
        out.println("</html></body>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        clearOrders();
        clearUsers();
        response.sendRedirect("/");
    }

    private void initPassword(){
        try{
            Scanner sc = new Scanner(new File("/Users/lukamitrovic/Desktop/VI Semestar/Web programiranje/Domaci/Domaci4/src/main/resources/security/password.txt"));
            while (sc.hasNextLine()){
                password = sc.nextLine();
            }
            System.out.println("Password loaded: " + this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearOrders(){
        ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> menu = (ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>>) getServletContext().getAttribute("order");
        menu.get("monday").clear();
        menu.get("tuesday").clear();
        menu.get("wednesday").clear();
        menu.get("thursday").clear();
        menu.get("friday").clear();
    }

    private void clearUsers(){
        CopyOnWriteArrayList<String> users = (CopyOnWriteArrayList<String>) getServletContext().getAttribute("users");
        users.clear();
    }
}
