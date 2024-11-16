package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Producto;
import com.followcargo.transporte.dao.Viaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ModeloViajes {

    private DataSource dataSource;

    public ModeloViajes(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Viaje getViajes(String id) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Viaje viaje = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM viajes WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int idViajes = rs.getInt("id");
                int idVehiculo = rs.getInt("id_vehiculo");
                int idConductor = rs.getInt("id_conductor");
                double costo = rs.getDouble("costo");
                String estado = rs.getString("estado");
                String fecha = rs.getString("fecha");
                String fechaCreacion = rs.getString("fecha_creacion");

                viaje = new Viaje(idViajes, idVehiculo, idConductor, costo, estado, fecha);
                viaje.setFechaCreacion(fechaCreacion);
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

        return viaje;

    }
    
    public List<Viaje> getListViajes() throws SQLException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        Viaje viaje = null;
        List<Viaje> listaViajes = new ArrayList<>();

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM viajes ORDER BY id ASC";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                int idViajes = rs.getInt("id");
                int idVehiculo = rs.getInt("id_vehiculo");
                int idConductor = rs.getInt("id_conductor");
                double costo = rs.getDouble("costo");
                String estado = rs.getString("estado");
                String fecha = rs.getString("fecha");
                String fechaCreacion = rs.getString("fecha_creacion");

                viaje = new Viaje(idViajes, idVehiculo, idConductor, costo, estado, fecha);
                viaje.setFechaCreacion(fechaCreacion);
                listaViajes.add(viaje);

            }

        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println(ex.getMessage());

        } finally {

            if (con != null) {
                con.close();
            }
            if (st != null) {
                st.close();
            }

        }

        return listaViajes;

    }
    
    public List<Producto> getListaProductosPorViaje(String idViaje) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Producto> listaProductosPorViaje = new ArrayList<>();

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM viajes_detalle_producto WHERE id_viaje=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, idViaje);

            rs = ps.executeQuery();

            while (rs.next()) {

                int idProducto = rs.getInt("id_producto");
                int cantidad =rs.getInt("cantidad");
                double precio = rs.getDouble("precio_total");
                Producto producto = new Producto();
                producto.setId(idProducto);
                producto.setCantidad(cantidad);
                producto.setPrecio(precio);
                listaProductosPorViaje.add(producto);

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

        return listaProductosPorViaje;

    }

}
