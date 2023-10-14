package com.domaci.domaci4;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private String message;
    private HashMap<String, ArrayList<String>> menu = new HashMap<>();
    private ArrayList<String> monday = new ArrayList<>();
    private ArrayList<String> tuesday = new ArrayList<>();
    private ArrayList<String> wednesday = new ArrayList<>();
    private ArrayList<String> thursday = new ArrayList<>();
    private ArrayList<String> friday = new ArrayList<>();


    public void init() {
        System.out.println("Servlet init");
        message = "Hello World!";

        ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> order = new ConcurrentHashMap<>();
        order.put("monday", new ConcurrentHashMap<>());
        order.put("tuesday", new ConcurrentHashMap<>());
        order.put("wednesday", new ConcurrentHashMap<>());
        order.put("thursday", new ConcurrentHashMap<>());
        order.put("friday", new ConcurrentHashMap<>());

        getServletContext().setAttribute("users", new CopyOnWriteArrayList<>());
        getServletContext().setAttribute("order", order);

        menu.put("monday", this.monday);
        initMenu("monday");
        menu.put("tuesday", this.tuesday);
        initMenu("tuesday");
        menu.put("wednesday", this.wednesday);
        initMenu("wednesday");
        menu.put("thursday", this.thursday);
        initMenu("thursday");
        menu.put("friday", this.friday);
        initMenu("friday");

        System.out.println("FOOD MENU: " + menu);


    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("service method");
        super.service(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>" +
                "<style>" +
                "button{" +
                "background-color: #4287f5;" +
                "}"+ "\n" +
                "table, th, td {\n" +
                        "  border:1px solid blue;\n" +
                        "}\n" +
                "</style>"
                +
                "<body>");
        CopyOnWriteArrayList<String> users = (CopyOnWriteArrayList<String>) getServletContext().getAttribute("users");
        if (!users.contains(request.getSession().getId())){
            out.println("<h1> Choose your food </h1>");
            out.println("<form action=\"submit\" method=\"post\">");
            //monday
            out.println("<label for=\"monday\">Monday: </label> <br> ");
            out.println("<select id=\"monday\" name=\"monday\" >");
            for (String meal : this.menu.get("monday")){
                out.println("<option value=\""+meal+"\">" + meal + "</option>");
            }
            out.println("</select> <br> ");
            //tuesday
            out.println("<label for=\"tuesday\">Tuesday: </label> <br> ");
            out.println("<select id=\"tuesday\" name=\"tuesday\" >");
            for (String meal : this.menu.get("tuesday")){
                out.println("<option value=\""+meal+"\">" + meal + "</option>");
            }
            out.println("</select> <br> ");
            //wednesday
            out.println("<label for=\"wednesday\">Wednesday: </label> <br> ");
            out.println("<select id=\"wednesday\" name=\"wednesday\" >");
            for (String meal : this.menu.get("wednesday")){
                out.println("<option value=\""+meal+"\">" + meal + "</option>");
            }
            out.println("</select> <br> ");
            //thursday
            out.println("<label for=\"thursday\">Thursday: </label> <br> ");
            out.println("<select id=\"thursday\" name=\"thursday\" >");
            for (String meal : this.menu.get("thursday")){
                out.println("<option value=\""+meal+"\">" + meal + "</option>");
            }
            out.println("</select> <br> ");
            //friday
            out.println("<label for=\"friday\">Friday: </label> <br> ");
            out.println("<select id=\"friday\" name=\"friday\" >");
            for (String meal : this.menu.get("friday")){
                out.println("<option value=\""+meal+"\">" + meal + "</option>");
            }
            out.println("</select> <br> ");

            out.println("<br/><button type=\"submit\">Confirm</button>");
            out.println("</form>");
        } else {
            out.println("<h1> You've already placed an order for this week. </h1>");

            out.println("<table style=\" width:100% \" ");

            out.println("<tr>");
            out.println("<th> Monday </th>");
            out.println("<th> Tuesday </th>");
            out.println("<th> Wednesday </th>");
            out.println("<th> Thursday </th>");
            out.println("<th> Friday </th>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>" + request.getSession().getAttribute("monday"));
            out.println("<td>" + request.getSession().getAttribute("tuesday"));
            out.println("<td>" + request.getSession().getAttribute("wednesday"));
            out.println("<td>" + request.getSession().getAttribute("thursday"));
            out.println("<td>" + request.getSession().getAttribute("friday"));
            out.println("</tr>");

            out.println("</table>");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }

    private void initMenu(String day){
        String dayTxt = day.concat(".txt");
        File daysMenu = new File("/Users/lukamitrovic/Desktop/VI Semestar/Web programiranje/Domaci/Domaci4/src/main/resources/days/"+dayTxt);
        try{
            Scanner sc = new Scanner(daysMenu);
            while (sc.hasNextLine()){
                String food = sc.nextLine();
                menu.get(day).add(food);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}