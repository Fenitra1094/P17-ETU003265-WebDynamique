package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;


import models.Depense;
import conn.Connexion;

public class DepenseDAO {
    public void save(Depense depense) throws SQLException {
        Connexion connect = new Connexion();
        Connection conn = connect.getConnexion();
        try{
            this.save(conn,depense);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
    }
    public void save(Connection conn,Depense depense) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO depense (idCredit, depense) VALUES (?, ?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, depense.getIdCredit());
            preparedStatement.setDouble(2, depense.getDepense());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
    }
    public double sommeDepense(int idCredit) throws SQLException {
        Connexion connect = new Connexion();
        Connection conn = connect.getConnexion();
        Double reponse = null;
        try{
           reponse =  this.sommeDepense(conn,idCredit);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return reponse;
    }
    public double sommeDepense(Connection conn, int idCredit) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Double total = 0.0; // Initialisé à 0.0 au lieu de null
        
        try {
            String query = "SELECT COALESCE(SUM(depense), 0) AS total FROM depense WHERE idCredit = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, idCredit);
            resultSet = preparedStatement.executeQuery(); // executeQuery() au lieu de executeUpdate()
            
            if (resultSet.next()) {
                total = resultSet.getDouble("total");
            }
        } finally {
            // Fermeture dans l'ordre inverse de création
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return total;
    }
}