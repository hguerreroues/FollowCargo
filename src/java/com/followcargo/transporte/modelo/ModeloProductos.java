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

public class ModeloProductos {
    
    private DataSource dataSource;

    public ModeloProductos(DataSource dataSource) {
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
                int cantidad = rs.getInt("cantidad");
                String unidadMedida = rs.getString("unidad_medida");
                double precio = rs.getDouble("precio");
                double alto = rs.getDouble("dimension_alto");
                double ancho = rs.getDouble("dimension_ancho");
                double largo = rs.getDouble("dimension_largo");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                dimensiones = new Dimensiones(alto, ancho, largo);
                dimensiones.setDimensionTotal(alto * ancho * largo);
                producto = new Producto(idProducto, nombre, descripcion, peso, cantidad, unidadMedida, precio, dimensiones, fechaCreacion);
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
                int cantidad = rs.getInt("cantidad");
                String unidadMedida = rs.getString("unidad_medida");
                double precio = rs.getDouble("precio");
                double alto = rs.getDouble("dimension_alto");
                double ancho = rs.getDouble("dimension_ancho");
                double largo = rs.getDouble("dimension_largo");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                dimensiones = new Dimensiones(alto, ancho, largo);
                dimensiones.setDimensionTotal(alto * ancho * largo);
                producto = new Producto(idProducto, nombre, descripcion, peso, cantidad, unidadMedida, precio, dimensiones, fechaCreacion);
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
    
    public boolean addProducto(Producto producto, String username) throws SQLException {

        boolean flag = false;

        Connection con = null;
        PreparedStatement ps = null;

        int lastInsertId = 0;

        int i = 1;

        try {

            con = dataSource.getConnection();

            String sql = "INSERT INTO producto(nombre, descripcion, peso, cantidad, unidad_medida, precio, dimension_alto, dimension_ancho, dimension_largo) VALUES (?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);

            ps.setString(i++, producto.getNombre());
            ps.setString(i++, producto.getDescripcion());
            ps.setDouble(i++, producto.getPeso());
            ps.setInt(i++, producto.getCantidad());
            ps.setString(i++, producto.getUnidadMedida());
            ps.setDouble(i++, producto.getPrecio());
            ps.setDouble(i++, producto.getDimensiones().getAlto());
            ps.setDouble(i++, producto.getDimensiones().getAncho());
            ps.setDouble(i++, producto.getDimensiones().getLargo());

            int row = ps.executeUpdate();

            if (row > 0) {

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lastInsertId = (int) generatedKeys.getLong(1); // Obtener el último ID insertado
                    }
                }

                producto.setId(lastInsertId);

                sql = "INSERT INTO logs (usuario, procedimiento, detalle_procedimiento) VALUES (?,?,?)";

                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, "INSERT INTO vehiculo");
                ps.setString(3, producto.toString());
                ps.execute();

                flag = true;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println(ex.getMessage());

        } finally {

            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }

        }

        return flag;

    }

}
