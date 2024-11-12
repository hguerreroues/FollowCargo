package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Geolocalizacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ModeloGeolocalizacion {
    
    private DataSource dataSource;

    public ModeloGeolocalizacion(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Geolocalizacion getGeoLocalizacion(String id) throws SQLException {


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Geolocalizacion geolocalizacion = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM rutas_geolocalizacion WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int idRutasGeolocalizacion = rs.getInt("id");
                int idRuta = rs.getInt("id_ruta");
                double latitud = rs.getDouble("latitud");
                double longitud = rs.getDouble("longitud");
                String descripcion = rs.getString("descripcion");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                geolocalizacion = new Geolocalizacion(idRutasGeolocalizacion, idRuta, latitud, longitud, descripcion);
                geolocalizacion.setFechaCreacion(fechaCreacion);
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

        return geolocalizacion;

    }
    
    public List<Geolocalizacion> getListasGeolocalizacion(String idRuta) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Geolocalizacion geolocalizacion = null;
        List<Geolocalizacion> listaGeolocalizacion = new ArrayList<>();

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM rutas_geolocalizacion WHERE id_ruta=? ORDER BY id ASC";
            ps = con.prepareStatement(sql);
            ps.setString(1, idRuta);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                
                int idRutasGeolocalizacion = rs.getInt("id");
                int idRutaInt = rs.getInt("id_ruta");
                double latitud = rs.getDouble("latitud");
                double longitud = rs.getDouble("longitud");
                String descripcion = rs.getString("descripcion");
                String fechaCreacion = rs.getString("fecha_creacion");
                
                geolocalizacion = new Geolocalizacion(idRutasGeolocalizacion, idRutaInt, latitud, longitud, descripcion);
                geolocalizacion.setFechaCreacion(fechaCreacion);
                listaGeolocalizacion.add(geolocalizacion);
                
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

        return listaGeolocalizacion;

    }

}
