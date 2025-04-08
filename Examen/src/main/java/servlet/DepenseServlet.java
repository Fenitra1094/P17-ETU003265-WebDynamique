package servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.Credit;
import dao.CreditDAO;
import models.Depense;
import dao.DepenseDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.DepenseService;

public class DepenseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        // Validation des paramètres
        String idCredit_ = req.getParameter("idCredit");
        String montant_ = req.getParameter("montant_depense");
        
        if(idCredit_ == null || montant_ == null || 
           idCredit_.isEmpty() || montant_.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                        "Paramètres manquants");
            return;
        }
        
        try {
            int idCredit = Integer.parseInt(idCredit_);
            double montant = Double.parseDouble(montant_);
            
            DepenseService service = new DepenseService();
            service.AjoutDepense(idCredit, montant);
            res.sendRedirect("index.html");
            
        } catch (NumberFormatException e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                        "Format numérique invalide");
        } catch (SQLException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                        "Erreur de base de données");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        CreditDAO creditDAO = new CreditDAO();
        DepenseDAO depenseDAO = new DepenseDAO();
        List<Credit> liste = new ArrayList<>();
        List<Credit> credit1 = null;
        
        try {
             credit1 = creditDAO.findAll();
            
        } catch (SQLException e) {
            e.printStackTrace();
            // On ne fait pas de forward après une erreur
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                         "Erreur de base de données");
        }
        try {
            for(Credit cre : credit1) { 
                int idd = cre.getId();
                String libelle = cre.getLibelle();
                Double reste = cre.getReste();
                double montant = depenseDAO.sommeDepense(idd);
                Credit creditt = new Credit(libelle, montant, reste);
                liste.add(creditt);
            }
            
            req.setAttribute("dashbord", liste);
              RequestDispatcher dispatcher = req.getRequestDispatcher("/web/liste.jsp"); // Chemin relatif
            dispatcher.forward(req, res);
          
       } catch (SQLException e) {
           e.printStackTrace();
           // On ne fait pas de forward après une erreur
           res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                        "aaa ty");
       }
        
    }
}