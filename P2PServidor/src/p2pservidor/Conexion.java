/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pservidor;

/**
 *
 * @author dennisvogels
 */
/* Necesitamos importar este paquete para acceder al conector JDBC */
import java.sql.*;

public class Conexion {

    public void test(String host, String database) throws Exception {
        /* Primero, comprobar si el driver de MySQL esta instalado */
        testDriver();

        /* Segundo, obtener conexion a la base de datos */
        Connection con = obtenerConexion(host, database);
        
        String nombre="denis";
        //String query = "SELECT nombreAmigo FROM Amigos WHERE id_producto = '"+ ventas.get(p).getIdProducto() +"'";
        /* Por este motivo (visualizacion) utilizamos un metodo diferente */
        ejecutarConsulta(con, "select * from Amigo where nombre='"+nombre+"'");


        /* Por ultimo cerramos la conexion con la base de datos */
        con.close();
    }

    /**
     *  Comprueba si el driver de conexion JDBC de MySQL esta instalado
     */
    protected void testDriver() throws Exception {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("Encontrado el driver de MySQL");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver no encontrado ... ");
            throw (e);
        }
    }

    /**
     *  Devuelve conexion a MySQL
     *
     */
    protected Connection obtenerConexion(String host, String database) throws Exception {
        String url = "";
        try {
            url = "jdbc:mysql://127.0.0.1:" + host + "/" + database;
            String usuario = "root";
            Connection con = DriverManager.getConnection(url, usuario,"");
            System.out.println("Conexion establecida con " + url + "...");
            return con;
        } catch (java.sql.SQLException e) {
            System.out.println("Conexion NO establecida con " + url);
            throw (e);
        }
    }

    /**
     *  Este metodo ejecuta una sentencia de actualizacion sobre la base de datos
     *  @param con database connection
     *  @param sqlStatement SQL DDL or DML statement to execute
     */
    protected void ejecutarOperacion(Connection con, String sqlStatement)
            throws Exception {
        try {
            Statement s = con.createStatement();
            s.execute(sqlStatement);
            s.close();
        } catch (SQLException e) {
            System.out.println("Error ejecutando sentencia SQL");
            throw (e);
        }
    }

    /**
     *  Este metodo ejecuta una sentencia de seleccion/consulta y muestra el resultado
     *  @param con database connection
     *  @param sqlStatement SQL SELECT statement to execute
     */
    protected void ejecutarConsulta(Connection con, String sqlStatement)
            throws Exception {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlStatement);
            while (rs.next()) {
                String nombre = (rs.getObject("nombreAmigo").toString());
                //String password = (rs.getObject("password").toString());
                //System.out.println("Registro encontrado : " + nombre + " " + password);
                System.out.println(nombre);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error ejecutando la sentencia SQL");
            throw (e);
        }
    }
}
