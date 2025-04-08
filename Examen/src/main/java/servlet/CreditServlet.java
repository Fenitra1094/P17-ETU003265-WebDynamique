package servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.Credit;
import dao.CreditDAO;
import java.sql.SQLException;

public class CreditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String libelle = req.getParameter("libelle_credit");
        String montant_ = req.getParameter("montant_credit");
        double montant = Double.parseDouble(montant_);
        Credit credit = new Credit(libelle, montant, montant);
        CreditDAO daooo = new CreditDAO();

        try {
            daooo.save(credit);
            res.sendRedirect("index.html");
        } catch (SQLException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'insertion dans la base de données");
        }
    }
}
