package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ModeloUsuario {
    
    private DataSource dataSource;

    public ModeloUsuario(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Usuario getUsuario(String nombreUsuario, String contra) throws SQLException {

        boolean flag = false;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Usuario usuario = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM usuario WHERE nombre_usuario=? and contra=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contra);

            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                //String nombreUsuario = rs.getString("nombre_usuario");
                //String contra = rs.getString("contra");
                String rol = rs.getString("rol");
                String nombreCompleto = rs.getString("nombre_completo");
                String estado = rs.getString("estado");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                usuario = new Usuario(id, nombreUsuario, contra, rol, nombreCompleto, estado);
            }
            

        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println(ex.getMessage());

        } finally {

            if (con != null) {
                con.close();
            }
            if(ps!= null) {
                ps.close();
            }

        }

        return usuario;

    }
    
    public boolean getLogin(String nombreUsuario, String contra) throws SQLException {

        boolean flag = false;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Usuario usuario = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM usuario WHERE nombre_usuario=? AND contra=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contra);

            rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            

        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println(ex.getMessage());

        } finally {

            if (con != null) {
                con.close();
            }
            if(ps!= null) {
                ps.close();
            }

        }

        return flag;

    }

}
