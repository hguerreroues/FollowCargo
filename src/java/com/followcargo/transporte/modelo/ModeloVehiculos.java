package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ModeloVehiculos {

    private DataSource dataSource;

    public ModeloVehiculos(DataSource dataSource) {
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
                int idConductor = rs.getInt("id_conductor_asignado");
                double costoFijoViaje = rs.getDouble("costo_fijo_viaje");
                String fechaCreacion = rs.getString("fecha_creacion");

                vehiculo = new Vehiculo(idVehiculo, tipo, marca, modelo, placa);
                vehiculo.setFechaCreacion(fechaCreacion);
                vehiculo.setIdConductor(idConductor);
                vehiculo.setCostoFijoViaje(costoFijoViaje);
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
                int idConductor = rs.getInt("id_conductor_asignado");
                double costoFijoViaje = rs.getDouble("costo_fijo_viaje");
                String fechaCreacion = rs.getString("fecha_creacion");

                vehiculo = new Vehiculo(idVehiculo, tipo, marca, modelo, placa);
                vehiculo.setFechaCreacion(fechaCreacion);
                vehiculo.setIdConductor(idConductor);
                vehiculo.setCostoFijoViaje(costoFijoViaje);
                listaVehiculo.add(vehiculo);

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

        return listaVehiculo;

    }

    public boolean addVehiculo(Vehiculo vehiculo, String username) throws SQLException {

        boolean flag = false;

        Connection con = null;
        PreparedStatement ps = null;

        int lastInsertId = 0;

        int i = 1;

        try {

            con = dataSource.getConnection();

            String sql = "INSERT INTO vehiculo(tipo, marca, modelo, placa, id_conductor_asignado, costo_fijo_viaje) VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);

            ps.setString(i++, vehiculo.getTipo());
            ps.setString(i++, vehiculo.getMarca());
            ps.setString(i++, vehiculo.getModelo());
            ps.setString(i++, vehiculo.getPlaca());
            ps.setInt(i++, vehiculo.getIdConductor());
            ps.setDouble(i++, vehiculo.getCostoFijoViaje());

            int row = ps.executeUpdate();

            if (row > 0) {

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lastInsertId = (int) generatedKeys.getLong(1); // Obtener el último ID insertado
                    }
                }

                vehiculo.setId(lastInsertId);

                sql = "INSERT INTO logs (usuario, procedimiento, detalle_procedimiento) VALUES (?,?,?)";

                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, "INSERT INTO vehiculo");
                ps.setString(3, vehiculo.toString());
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
