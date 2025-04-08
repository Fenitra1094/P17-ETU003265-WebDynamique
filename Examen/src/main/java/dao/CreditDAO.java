package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Credit;
import conn.Connexion;

public class CreditDAO {
    public void save(Credit credit) throws SQLException {
        Connexion connect = new Connexion();
        Connection conn = connect.getConnexion();
        try{
            this.save(conn,credit);
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
    public void save(Connection conn,Credit credit) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO credit (libelle, montant, reste) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, credit.getLibelle());
            preparedStatement.setDouble(2, credit.getMontant());
            preparedStatement.setDouble(3, credit.getReste());
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
    public void update(Credit credit) throws SQLException {
        Connexion connect = new Connexion();
        Connection conn = connect.getConnexion();
        try{
            this.update(conn,credit);
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

    public void update(Connection conn, Credit credit) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE credit SET libelle = ?, montant = ?, reste = ? WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, credit.getLibelle());
            preparedStatement.setDouble(2, credit.getMontant());
            preparedStatement.setDouble(3, credit.getReste());
            preparedStatement.setInt(4, credit.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
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
    
    public Credit findById(int id)throws SQLException {
        Connexion connect = new Connexion();
        Connection conn = connect.getConnexion();
        Credit credit = null;
        try{
            credit = findById(conn, id);
            
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
        return credit;
    }
    public Credit findById(Connection conn, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Credit credit = null;
        try{
            String query = "SELECT * FROM credit WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String libelle = resultSet.getString("libelle");
                Double montant = resultSet.getDouble("montant");
                Double reste =  resultSet.getDouble("reste");
                
                credit = new Credit(libelle,montant ,reste);
                credit.setId(resultSet.getInt("id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return credit;
        
    }
    public List<Credit> findAll() throws SQLException {
        Connexion connect = new Connexion();
        Connection conn = connect.getConnexion();
        List<Credit> credits = new ArrayList<>();
        try{
            credits = findAll(conn);
            
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
        return credits;
    }

    public List<Credit> findAll(Connection conn) throws SQLException {
        List<Credit> credits = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM credit");
            while (resultSet.next()) {
                String libelle = resultSet.getString("libelle");
                Double montant = resultSet.getDouble("montant");
                Double reste =  resultSet.getDouble("reste");
                
                Credit credit = new Credit(libelle,montant ,reste);
                credit.setId(resultSet.getInt("id"));
                credits.add(credit);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return credits;
    }

    


}
