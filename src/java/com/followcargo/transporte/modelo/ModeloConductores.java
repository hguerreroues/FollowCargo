package com.followcargo.transporte.modelo;

import com.followcargo.transporte.dao.Conductor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ModeloConductores {

    private DataSource dataSource;

    public ModeloConductores(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Conductor getConductor(String idConductor) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Conductor conductor = null;

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM conductores WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, idConductor);

            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String licencia = rs.getString("licencia");
                String fechaLicencia = rs.getString("fecha_licencia");
                String vencimientoLicencia = rs.getString("vencimiento_licencia");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String estado = rs.getString("estado");
                String ubicacionActual = rs.getString("ubicacion_actual");
                int idUltimaRuta = rs.getInt("id_ultima_ruta");

                conductor = new Conductor(id,  nombres,  apellidos,  licencia,  fechaLicencia,  vencimientoLicencia,  telefono,  email,  estado,  ubicacionActual, idUltimaRuta);
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

        return conductor;

    }

   
    public List<Conductor> getListaConductores() throws SQLException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        Conductor conductor = null;
        List<Conductor> listaConductores = new ArrayList<>();

        try {

            con = dataSource.getConnection();

            String sql = "SELECT * FROM conductores WHERE estado='1' ORDER BY id ASC";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String licencia = rs.getString("licencia");
                String fechaLicencia = rs.getString("fecha_licencia");
                String vencimientoLicencia = rs.getString("vencimiento_licencia");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String estado = rs.getString("estado");
                String ubicacionActual = rs.getString("ubicacion_actual");
                int idUltimaRuta = rs.getInt("id_ultima_ruta");

                conductor = new Conductor(id,  nombres,  apellidos,  licencia,  fechaLicencia,  vencimientoLicencia,  telefono,  email,  estado,  ubicacionActual, idUltimaRuta);
                listaConductores.add(conductor);
                
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

        return listaConductores;

    }
    
    
    public boolean addConductor(Conductor conductor, String username) throws SQLException {

        boolean flag = false;

        Connection con = null;
        PreparedStatement ps = null;

        int lastInsertId = 0;

        int i = 1;

        try {

            con = dataSource.getConnection();

            String sql = "INSERT INTO conductores(nombres, apellidos, licencia, fecha_licencia, vencimiento_licencia, telefono, email) VALUES (?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);

            ps.setString(i++, conductor.getNombres());
            ps.setString(i++, conductor.getApellidos());
            ps.setString(i++, conductor.getLicencia());
            ps.setString(i++, conductor.getFechaLicencia());
            ps.setString(i++, conductor.getVencimientoLicencia());
            ps.setString(i++, conductor.getTelefono());
            ps.setString(i++, conductor.getEmail());

            int row = ps.executeUpdate();

            if (row > 0) {

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lastInsertId = (int) generatedKeys.getLong(1); // Obtener el último ID insertado
                    }
                }

                conductor.setId(lastInsertId);

                sql = "INSERT INTO logs (usuario, procedimiento, detalle_procedimiento) VALUES (?,?,?)";

                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, "INSERT INTO conductor");
                ps.setString(3, conductor.toString());
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
