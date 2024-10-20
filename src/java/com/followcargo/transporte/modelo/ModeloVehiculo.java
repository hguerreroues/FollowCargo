package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Dimensiones;
import com.followcargo.transporte.dao.Producto;
import com.followcargo.transporte.dao.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ModeloVehiculo {
    
    private DataSource dataSource;

    public ModeloVehiculo(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Vehiculo getVehiculo(String id) throws SQLException {


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Vehiculo vehiculo = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM vehiculo WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int idVehiculo = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String placa = rs.getString("placa");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                vehiculo = new Vehiculo(idVehiculo,tipo, marca, modelo, placa);
                vehiculo.setFechaCreacion(fechaCreacion);
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

        return vehiculo;

    }
    
    public List<Vehiculo> getListaVehiculos() throws SQLException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        Vehiculo vehiculo = null;
        List<Vehiculo> listaVehiculo = new ArrayList<>();

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM vehiculo ORDER BY id ASC";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                
                int idVehiculo = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String placa = rs.getString("placa");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                vehiculo = new Vehiculo(idVehiculo,tipo, marca, modelo, placa);
                vehiculo.setFechaCreacion(fechaCreacion);
                listaVehiculo.add(vehiculo);
                
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

        return listaVehiculo;

    }

}
