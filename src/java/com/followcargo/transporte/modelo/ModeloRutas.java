package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Geolocalizacion;
import com.followcargo.transporte.dao.Producto;
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
            if (ps != null) {
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
            if (st != null) {
                st.close();
            }

        }

        return listaRutas;

    }

    public boolean addRuta(Ruta ruta, String username) throws SQLException {

        boolean flag = false;

        Connection con = null;
        PreparedStatement ps = null;

        int lastInsertIdViaje = 0;
        int lastInsertIdRuta = 0;

        int i = 1;
        int row = 0;

        try {

            con = dataSource.getConnection();

            //Se agrega el viaje, este me genera el idViaje para almacenar en la tabla viajes_detalle_producto 
            String sql = "INSERT INTO viajes(id_vehiculo, id_conductor, costo, estado, fecha) VALUES (?,?,?,?,?)";

            ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);

            ps.setInt(i++, ruta.getViaje().getIdVehiculo());
            ps.setInt(i++, ruta.getViaje().getIdConductor());
            ps.setDouble(i++, ruta.getViaje().getCosto());
            ps.setString(i++, ruta.getViaje().getEstado());
            ps.setString(i++, ruta.getViaje().getFecha());

            row = ps.executeUpdate();

            if (row > 0) {

                row = 0;

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lastInsertIdViaje = (int) generatedKeys.getLong(1); // Obtener el último ID insertado
                    }
                }

                ruta.getViaje().setId(lastInsertIdViaje);
                ruta.setIdViaje(lastInsertIdViaje);

                //Se almacena el detalle de los productos, cantidad y precio del producto.
                sql = "INSERT INTO viajes_detalle_producto(id_viaje, id_producto, cantidad, precio_total) VALUES (?,?,?,?)";

                if (ruta.getViaje().getListaProductos().size() > 0) {
                    for (Producto p : ruta.getViaje().getListaProductos()) {

                        ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
                        i = 1;

                        ps.setInt(i++, ruta.getViaje().getId());
                        ps.setInt(i++, p.getId());
                        ps.setInt(i++, p.getCantidad());
                        ps.setDouble(i++, p.getPrecio());
                        ps.execute();

                    }
                }

                //Se almacena la ruta, donde ya tenemos el viaje creado
                sql = "INSERT INTO rutas(id_viaje, origen, destino, distancia) VALUES (?,?,?,?)";

                ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
                i = 1;

                ps.setInt(i++, ruta.getIdViaje());
                ps.setString(i++, ruta.getOrigen());
                ps.setString(i++, ruta.getDestino());
                ps.setDouble(i++, ruta.getDistancia());

                row = ps.executeUpdate();

                if (row > 0) {

                    row = 0;

                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            lastInsertIdRuta = (int) generatedKeys.getLong(1); // Obtener el último ID insertado
                        }
                    }

                    ruta.setId(lastInsertIdRuta);

                    //Se almacena la geolocalizacion inicial
                    sql = "INSERT INTO rutas_geolocalizacion(id_ruta, latitud, longitud, descripcion) VALUES(?,?,?,?)";

                    if (ruta.getListaGeoLocalizacion().size() > 0) {
                        for (Geolocalizacion g : ruta.getListaGeoLocalizacion()) {

                            g.setIdRuta(ruta.getId());

                            ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
                            i = 1;

                            ps.setInt(i++, g.getIdRuta());
                            ps.setDouble(i++, g.getLatitud());
                            ps.setDouble(i++, g.getLongitud());
                            ps.setString(i++, g.getDescripcion());
                            ps.execute();

                        }
                    }
                    
                    //Se actualiza el Id de la ultima ruta.
                    sql = "UPDATE conductores SET id_ultima_ruta=? WHERE id=?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, ruta.getId());
                    ps.setInt(2, ruta.getViaje().getIdConductor());
                    ps.execute();
                    

                    //Se guarda el registro de la ruta creada.
                    sql = "INSERT INTO logs (usuario, procedimiento, detalle_procedimiento) VALUES (?,?,?)";

                    ps = con.prepareStatement(sql);
                    ps.setString(1, username);
                    ps.setString(2, "INSERT INTO rutas");
                    ps.setString(3, ruta.toString());
                    row = ps.executeUpdate();

                }

                flag = true;

            } else {
                flag = false;
            }

            //String sql = "INSERT INTO ruta(tipo, marca, modelo, placa) VALUES (?,?,?,?)";
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
