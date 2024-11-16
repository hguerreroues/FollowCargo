package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Ruta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ModeloRutas {
    
    private DataSource dataSource;

    public ModeloRutas(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Ruta getRuta(String id) throws SQLException {


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Ruta ruta = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM rutas WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int idRuta = rs.getInt("id");
                int idViaje = rs.getInt("id_viaje");
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                double distancia = rs.getDouble("distancia");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                ruta = new Ruta(idRuta, idViaje, origen, destino, distancia);
                ruta.setFechaCreacion(fechaCreacion);
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

        return ruta;

    }
    
    public List<Ruta> getListaRutas() throws SQLException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        Ruta ruta = null;
        List<Ruta> listaRutas = new ArrayList<>();

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM rutas ORDER BY id ASC";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                
                int idRuta = rs.getInt("id");
                int idViaje = rs.getInt("id_viaje");
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                double distancia = rs.getDouble("distancia");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                ruta = new Ruta(idRuta, idViaje, origen, destino, distancia);
                ruta.setFechaCreacion(fechaCreacion);
                listaRutas.add(ruta);
                
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

        return listaRutas;

    }

}
