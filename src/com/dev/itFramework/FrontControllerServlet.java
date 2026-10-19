package com.dev.itFramework;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.List;
import java.lang.Override;
import java.util.ArrayList;
import com.dev.itFramework.Controller;
import com.dev.itFramework.utils.ClassMethod;
import com.dev.itFramework.utils.ListeControllerAnnote;

public class FrontControllerServlet extends HttpServlet
{

    private List<Class<?>> controllers;
    private Map<String, ClassMethod> mappingUrls;

    @Override
    public void init() throws ServletException {
        String initTestControllers = this.getInitParameter("initTestController");
        controllers = new ArrayList<>();
        try {
            for (String p : initTestControllers.split(";")) {
                controllers.addAll(ListeControllerAnnote.getController(Controller.class, p));
            }
            mappingUrls = ListeControllerAnnote.getMapping(controllers);
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
        // String methode = request.getMethod();

        out.println("<html>");
        out.println("<head><title>S4_framework</title></head>");
        out.println("<body>");

        String path = request.getServletPath();

        if (mappingUrls != null && mappingUrls.containsKey(path)) {
            ClassMethod mapping = mappingUrls.get(path);

            out.println("<h1>Ça marche!</h1>");
            out.println("<p><strong>Méthode:</strong> " + request.getMethod() + "</p>");
            out.println("<p><strong>Route:</strong> " + path + "</p>");
            out.println("<p><strong>Action:</strong> " + mapping.getClazz().getName() + "::" + mapping.getMethode().getName() + "</p>");
        } else {
            out.println("<h1>Route inconnue!</h1>");
            out.println("<p>");
            out.println("<strong>Actions connues:</strong>");
            out.println("<ul>");

            if (mappingUrls != null && !mappingUrls.isEmpty()) {
                for (String url : mappingUrls.keySet()) {
                    ClassMethod mapping = mappingUrls.get(url);
                    out.println("<li>" + url + " → " + mapping.getClazz().getName() + "::" + mapping.getMethode().getName() + "</li>");
                }
            } else {
                out.println("<li>Aucune route trouvée</li>");
            }

            out.println("</ul>");
            out.println("</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
