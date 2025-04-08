package service;
import models.Credit;
import models.Depense;
import dao.CreditDAO;
import dao.DepenseDAO;

import java.sql.Connection;
import java.sql.SQLException;
import conn.Connexion;
public class DepenseService {
    public void AjoutDepense(int idCredit, double montant) throws SQLException {
        Connexion connect = new Connexion();
        Connection connection = connect.getConnexion();
        Credit credit = null;
        CreditDAO creditDAO = new CreditDAO();
        DepenseDAO depenseDAO = new DepenseDAO();
        try{
            connection.setAutoCommit(false);
            credit = creditDAO.findById(idCredit);
            
            if(credit == null )
            {
                throw new IllegalArgumentException("Le credit n'existe pas");
            }
           String libelle = credit.getLibelle();
           Double montant_credit = credit.getMontant();
            Double reste = credit.getReste();
           int id = credit.getId();
    
            if(reste < montant){
                throw new IllegalArgumentException("Votre solde est insuffisant ");
            }
                Double m = reste - montant;
                Credit creditModif = new Credit(libelle, montant_credit ,m);
                creditModif.setId(id);
                creditDAO.update(creditModif);

                Depense nouveauDep = new Depense(id, montant);
                depenseDAO.save(nouveauDep);
                connection.commit();
            

        }
        catch(SQLException e){
            e.printStackTrace();
            connection.rollback();
            throw new IllegalArgumentException("L'operation n'a pas pu etre effectue");
        }
        finally{
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }

    
        
    }
    
}
