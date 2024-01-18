package Clases;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Conexion {

    private Connection con;
    private String url = "db.s3db";
    private String driver = "org.sqlite.JDBC";

    public Conexion() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:" + url);
        if (con != null) {
            System.out.println("Conexion exitosa");
        } else {
            System.out.println("Error al conectar");
        }
    }

    public Connection getConexion() {
        return con;
    }

    public ResultSet listar(String query) {
        try {
            Class.forName(driver).newInstance();
            PreparedStatement da = con.prepareStatement(query);
            ResultSet tbl = da.executeQuery();
            return tbl;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean ejecutar(String Cad) {
        System.out.println(Cad);
        try {
            Class.forName(driver).newInstance();
            PreparedStatement da = con.prepareStatement(Cad);
            da.executeUpdate();
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String aux = getMD5("admin");
        if (aux.equals("21232f297a57a5a743894a0e4a801fc3")) {
            System.out.println("si");
        }
        //System.out.println(getMD5("admin"));
    }
}
