/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Timle
 */
public class ShoppingListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = (String) request.getSession().getAttribute("name");
        
        String action = (String) request.getParameter("action");
        
        
        if ( action != null && action.equals("logout")) {
        
            request.getSession().invalidate();
            
            response.sendRedirect("shoppingList");
            
            return;
            
        // END OF IF 1
        }
        
        if (name != null) {
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        
        // END OF IF
        } else {
        
            this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            
        // END OF ELSE
        }
        
        
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action.equals("register")) {
        
             String name = request.getParameter("name");

            if (name != null) {
                // Creates the session
                request.getSession().setAttribute("name", name);
                
                ArrayList<String> items = new ArrayList<>();
                request.getSession().setAttribute("items", items);
                
                /*
                 * ^ this is the same as the one below
                 */
                
                // request.getSession().setAttribute("items", new ArrayList<String>());




            // END OF IF 2
            }
            
            
        // END OF IF 1
        } else if (action.equals("add")) {
            
            String item = request.getParameter("item");
            
            // Adds the item
            
            ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
            
            items.add(item);
            
            request.getSession().setAttribute("items", items);
            
            
        
        // END OF ELSE IF 1
        } else if (action.equals("delete")) {
        
            String itemValue = request.getParameter("item");
            
            ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
            
            // items.remove(itemValue); this is the quick version
            
            // long version below
            
            int indexToDelete = -1;
            
            for (int loop1 = 0; loop1 < items.size(); loop1++) {
            
                if (items.get(loop1).equals(itemValue)) {
                
                    indexToDelete = loop1;
                    break;
                    
                // END OF IF 1
                }
                
            // END OF FOR LOOP 1
            }
            
            if (indexToDelete != -1) {
            
                items.remove(indexToDelete);
            
            // END OF IF 1
            }
            
            request.getSession().setAttribute("items", items);

            
        // END OF ELSE IF 2
        } 
        
        
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }

    

}
