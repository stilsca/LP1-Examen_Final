package Clases;

import java.sql.SQLException;
import java.sql.ResultSet;

public class Session {

    private Conexion con;
    private int idUsuario;
    private String usuario;

    public Session() throws SQLException {
        try {
            con = new Conexion();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void ejecutar(String cadena) {
        con.ejecutar(cadena);
    }

    public ResultSet listar(String cadena) {
        return con.listar(cadena);
    }

}
