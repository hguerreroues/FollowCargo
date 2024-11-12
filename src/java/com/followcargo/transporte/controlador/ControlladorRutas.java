package com.followcargo.transporte.controlador;

import com.followcargo.transporte.dao.Conductor;
import com.followcargo.transporte.dao.Geolocalizacion;
import com.followcargo.transporte.dao.Producto;
import com.followcargo.transporte.dao.Ruta;
import com.followcargo.transporte.dao.Vehiculo;
import com.followcargo.transporte.dao.Viaje;
import com.followcargo.transporte.modelo.ModeloConductores;
import com.followcargo.transporte.modelo.ModeloGeolocalizacion;
import com.followcargo.transporte.modelo.ModeloProductos;
import com.followcargo.transporte.modelo.ModeloRutas;
import com.followcargo.transporte.modelo.ModeloVehiculos;
import com.followcargo.transporte.modelo.ModeloViajes;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ControlladorRutas", urlPatterns = {"/Rutas", "/rutas"})
public class ControlladorRutas extends HttpServlet {

    private ModeloVehiculos modeloVehiculo;
    private ModeloRutas modeloRutas;
    private ModeloViajes modeloViajes;
    private ModeloProductos modeloProductos;
    private ModeloGeolocalizacion modeloGeolocalizacion;
    private ModeloConductores modeloConductores;
    private ModeloVehiculos modeloVehiculos;

    @Resource(name = "jdbc/FollowCargo")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        modeloVehiculo = new ModeloVehiculos(dataSource);
        modeloRutas = new ModeloRutas(dataSource);
        modeloViajes = new ModeloViajes(dataSource);
        modeloProductos = new ModeloProductos(dataSource);
        modeloGeolocalizacion = new ModeloGeolocalizacion(dataSource);
        modeloConductores = new ModeloConductores(dataSource);
        modeloVehiculos = new ModeloVehiculos(dataSource);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (StringUtils.isBlank(action)) {
            action = "list";
        }

        switch (action) {

            case "list":
                getListaRutas(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (StringUtils.isBlank(action)) {
            action = "list";
        }

        switch (action) {

            case "list":
                getListaRutas(request, response);
                break;

            case "get":
                getRuta(request, response);
                break;

            case "add":
                addRuta(request, response);
                break;

        }
    }

    private void getListaRutas(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Ruta> listaRutas = new ArrayList<>();
        List<Producto> listaProductosPorViaje = new ArrayList<>();
        List<Geolocalizacion> listaGeoLocalizacion = new ArrayList<>();
        Viaje viaje = null;

        Producto producto = null;

        try {

            listaRutas = modeloRutas.getListaRutas();
            if (listaRutas.size() > 0) {
                for (Ruta r : listaRutas) {
                    String idRuta = String.valueOf(r.getId());
                    System.out.println("idRuta= " + idRuta);

                    String idViaje = String.valueOf(r.getIdViaje());
                    viaje = modeloViajes.getViajes(idViaje);
                    r.setViaje(viaje);

                    if (viaje != null) {
                        listaProductosPorViaje = new ArrayList<>();

                        //Se agrega los productos al viaje
                        listaProductosPorViaje = modeloViajes.getListaProductosPorViaje(idViaje);
                        if (listaProductosPorViaje.size() > 0) {
                            for (Producto p : listaProductosPorViaje) {
                                producto = modeloProductos.getProducto(String.valueOf(p.getId()));
                                p.setNombre(producto.getNombre());
                                p.setDescripcion(producto.getDescripcion());
                                p.setPeso(producto.getPeso());
                                p.setUnidadMedida(producto.getUnidadMedida());
                                p.setDimensiones(producto.getDimensiones());
                            }
                            viaje.setListaProductos(listaProductosPorViaje);
                        }

                    }

                    //Se agrega las geolocalizaciones
                    listaGeoLocalizacion = modeloGeolocalizacion.getListasGeolocalizacion(idRuta);
                    r.setListaGeoLocalizacion(listaGeoLocalizacion);

                }
            }

            String json = new Gson().toJson(listaRutas);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void getRuta(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Ruta ruta = null;
        List<Producto> listaProductosPorViaje = new ArrayList<>();
        List<Geolocalizacion> listaGeoLocalizacion = new ArrayList<>();
        Viaje viaje = null;

        Producto producto = null;

        try {

            String idRuta = request.getParameter("id");
            if (!StringUtils.isBlank(idRuta)) {

                ruta = modeloRutas.getRuta(idRuta);

                String idViaje = String.valueOf(ruta.getIdViaje());
                viaje = modeloViajes.getViajes(idViaje);
                ruta.setViaje(viaje);

                if (viaje != null) {
                    
                    Conductor conductor = modeloConductores.getConductor(String.valueOf(viaje.getIdConductor()));
                    Vehiculo vehiculo = modeloVehiculos.getVehiculo(String.valueOf(viaje.getIdConductor()));
                    viaje.setConductor(conductor);
                    viaje.setVehiculo(vehiculo);
                    
                    listaProductosPorViaje = new ArrayList<>();

                    //Se agrega los productos al viaje
                    listaProductosPorViaje = modeloViajes.getListaProductosPorViaje(idViaje);
                    if (listaProductosPorViaje.size() > 0) {
                        for (Producto p : listaProductosPorViaje) {
                            producto = modeloProductos.getProducto(String.valueOf(p.getId()));
                            p.setNombre(producto.getNombre());
                            p.setDescripcion(producto.getDescripcion());
                            p.setPeso(producto.getPeso());
                            p.setUnidadMedida(producto.getUnidadMedida());
                            p.setDimensiones(producto.getDimensiones());
                        }
                        viaje.setListaProductos(listaProductosPorViaje);
                    }

                }

                //Se agrega las geolocalizaciones
                listaGeoLocalizacion = modeloGeolocalizacion.getListasGeolocalizacion(idRuta);
                ruta.setListaGeoLocalizacion(listaGeoLocalizacion);

            }

            String json = new Gson().toJson(ruta);
            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void addRuta(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Ruta ruta = null;

        boolean flag = false;

        Gson gson = new Gson();

        try {

            InputStream in = new BufferedInputStream(request.getInputStream());

            String result = IOUtils.toString(in, "UTF-8");

            ruta = gson.fromJson(result, Ruta.class);

            String usuario = request.getParameter("usuario");

            System.out.println(ruta);

            flag = modeloRutas.addRuta(ruta, usuario);
            String json = new Gson().toJson(flag);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
