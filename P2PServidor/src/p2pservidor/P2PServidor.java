/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pservidor;

/**
 *
 * @author dennisvogels
 */
public class P2PServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion con = new Conexion();
        try{
        con.test("3306", "chat");
        }catch(Exception e){}
    }
}
