package com.dev.itFramework;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.Override;
import java.util.ArrayList;
import java.util.List;
import com.dev.itFramework.utils.ListeControllerAnnote;
import com.dev.itFramework.Controller;

public class FrontControllerServlet extends HttpServlet
{

    private List<Class<?>> controllers;

    @Override
    public void init() throws ServletException {
        String initTestControllers = this.getInitParameter("initTestController");
        controllers = new ArrayList<>();
        try {
            for (String p : initTestControllers.split(";")) {
                controllers.addAll(ListeControllerAnnote.getController(Controller.class, p));
            }
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String path = request.getServletPath();
        String methode = request.getMethod();

        out.println("ETU004331");
        out.println("OK sprint1!");
        out.println("Methode = " + methode);
        out.println("URL = " + path);
        for (Class<?> controller : controllers) {
            out.println(controller.getName());
        }
    }
}
