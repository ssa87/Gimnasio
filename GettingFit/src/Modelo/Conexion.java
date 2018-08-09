package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
    public static Connection conexionPostgresql()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection connection = (Connection)DriverManager.getConnection("jdbc:postgresql://localhost:5432/gimnasio", "postgres","admin");
            return connection;
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
