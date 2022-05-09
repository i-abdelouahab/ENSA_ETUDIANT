import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



 
public class ConnexionBasedonne{

    Connection con;

    public ConnexionBasedonne() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e); // pour afficher l'erreur
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ServiceEtudiant", "root", "");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    Connection obtenirconnexion(){ return con;}
}