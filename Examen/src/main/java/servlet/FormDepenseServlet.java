package servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.Credit;
import dao.CreditDAO;
/* import models.Depense;
import dao.DepenseDAO; */
import java.sql.SQLException;
import java.util.List;

public class FormDepenseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException { 

        CreditDAO creditDAO = new CreditDAO();
        try {
            List<Credit> credit_existant = creditDAO.findAll();
            req.setAttribute("credit_existant", credit_existant);
           
            RequestDispatcher dispatcher = req.getRequestDispatcher("/web/depense.jsp"); // Chemin corrigé
            dispatcher.forward(req, res);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
} 
