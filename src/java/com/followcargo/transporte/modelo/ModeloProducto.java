package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Dimensiones;
import com.followcargo.transporte.dao.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ModeloProducto {
    
    private DataSource dataSource;

    public ModeloProducto(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Producto getProducto(String id) throws SQLException {


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Producto producto = null;
        Dimensiones dimensiones = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM producto WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int idProducto = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double peso = rs.getDouble("peso");
                double alto = rs.getDouble("alto");
                double ancho = rs.getDouble("ancho");
                double largo = rs.getDouble("largo");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                dimensiones = new Dimensiones(alto, ancho, largo);
                producto = new Producto(idProducto, nombre, descripcion, peso, dimensiones);
                producto.setFechaCreacion(fechaCreacion);
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

        return producto;

    }
    
    public List<Producto> getListaProductos() throws SQLException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        Producto producto = null;
        List<Producto> listaProducto = new ArrayList<>();
        Dimensiones dimensiones = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM producto ORDER BY id ASC";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                
                int idProducto = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double peso = rs.getDouble("peso");
                double alto = rs.getDouble("alto");
                double ancho = rs.getDouble("ancho");
                double largo = rs.getDouble("largo");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                dimensiones = new Dimensiones(alto, ancho, largo);
                producto = new Producto(idProducto, nombre, descripcion, peso, dimensiones);
                producto.setFechaCreacion(fechaCreacion);
                listaProducto.add(producto);
                
            }
            

        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println(ex.getMessage());

        } finally {

            if (con != null) {
                con.close();
            }
            if(st!= null) {
                st.close();
            }

        }

        return listaProducto;

    }

}
