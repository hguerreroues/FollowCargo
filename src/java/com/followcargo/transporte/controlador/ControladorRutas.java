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
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ControladorRutas", urlPatterns = {"/Rutas", "/rutas"})
public class ControladorRutas extends HttpServlet {

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

            case "addLocal":
                addRutaLocal(request, response);
                break;

            case "addItem":
                addProductosItems(request, response);
                break;

            case "deleteItem":
                deleteItem(request, response);
                break;

        }
    }

    private void getListaRutas(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

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

        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

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

        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

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

    private void addRutaLocal(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(true);

        List<Producto> invoiceItemList = (List<Producto>) session.getAttribute("InvoiceItems");
        Producto producto = null;
        double totalInvoice = 0;

        if (invoiceItemList == null) {
            invoiceItemList = new ArrayList<Producto>();
            session.setAttribute("InvoiceItems", invoiceItemList);
        }

        List<Geolocalizacion> listGeolocalizacion = new ArrayList<>();
        Geolocalizacion geo = null;
        Ruta ruta = null;

        Viaje viaje = null;

        double latOrigen = 0;
        double lngOrigen = 0;
        double latDestino = 0;
        double lngDestino = 0;
        double distancia = 0;

        double costoTotal = 0;

        int idVehiculo = 0;
        int idConductor = 0;

        boolean flag = false;

        Gson gson = new Gson();

        try {

            String fechaViaje = request.getParameter("fechaViaje");
            String vehiculo = request.getParameter("vehiculo");
            if (!StringUtils.isBlank(vehiculo)) {
                idVehiculo = Integer.parseInt(vehiculo);
            }
            String conductor = request.getParameter("conductor");
            if (!StringUtils.isBlank(conductor)) {
                idConductor = Integer.parseInt(conductor);
            }
            String origen = request.getParameter("origen");
            String latitudOrigen = request.getParameter("latitudOrigen");
            if (!StringUtils.isBlank(latitudOrigen)) {
                latOrigen = Double.parseDouble(latitudOrigen);
            }
            String longitudOrigen = request.getParameter("longitudOrigen");
            if (!StringUtils.isBlank(longitudOrigen)) {
                lngOrigen = Double.parseDouble(longitudOrigen);
            }
            String destino = request.getParameter("destino");
            String latitudDestino = request.getParameter("latitudDestino");
            if (!StringUtils.isBlank(latitudDestino)) {
                latDestino = Double.parseDouble(latitudDestino);
            }
            String longitudDestino = request.getParameter("longitudDestino");
            if (!StringUtils.isBlank(longitudDestino)) {
                lngDestino = Double.parseDouble(longitudDestino);
            }
            String distanciaStr = request.getParameter("distancia");
            if (!StringUtils.isBlank(distanciaStr)) {
                distancia = Double.parseDouble(distanciaStr);
            }

            String costoTotalInput = request.getParameter("costoTotalInput");
            if (!StringUtils.isBlank(costoTotalInput)) {
                costoTotal = Double.parseDouble(costoTotalInput);
            }

            geo = new Geolocalizacion();
            geo.setDescripcion("Origen");
            geo.setLatitud(latOrigen);
            geo.setLongitud(lngOrigen);
            listGeolocalizacion.add(geo);

            geo = new Geolocalizacion();
            geo.setDescripcion("Destino");
            geo.setLatitud(latDestino);
            geo.setLongitud(lngDestino);
            listGeolocalizacion.add(geo);

            viaje = new Viaje();
            viaje.setIdVehiculo(idVehiculo);
            viaje.setIdConductor(idConductor);
            viaje.setCosto(costoTotal);
            viaje.setEstado("Creado");
            viaje.setFecha(fechaViaje);
            viaje.setListaProductos(invoiceItemList);

            ruta = new Ruta();
            ruta.setOrigen(origen);
            ruta.setDestino(destino);
            ruta.setDistancia(distancia);
            ruta.setListaGeoLocalizacion(listGeolocalizacion);
            ruta.setViaje(viaje);

            String usuario = request.getParameter("usuario");

            System.out.println(ruta);

            flag = modeloRutas.addRuta(ruta, usuario);
            if (flag) {
                session.removeAttribute("InvoiceItems");
                session = request.getSession(false);

                response.sendRedirect("listado-rutas");

            } else {
                response.sendRedirect("crear-ruta");
            }

            //String json = new Gson().toJson(flag);
            //response.getWriter().write(json);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addProductosItems(HttpServletRequest request, HttpServletResponse response) {

        String pattern = "#,###,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setGroupingSize(3);

        HttpSession session = request.getSession(true);
        int id = 0;
        int cantidad = 0;
        double precioTotal = 0;
        double pesoTotal = 0;
        double totalPeso = 0;
        List<Producto> invoiceItemList = (List<Producto>) session.getAttribute("InvoiceItems");
        Producto producto = null;
        double totalInvoice = 0;

        if (invoiceItemList == null) {
            invoiceItemList = new ArrayList<Producto>();
            session.setAttribute("InvoiceItems", invoiceItemList);
        }

        try {

            String idProducto = request.getParameter("idProducto");
            if (!StringUtils.isBlank(idProducto)) {
                id = Integer.parseInt(idProducto);
            }
            String cantidadProducto = request.getParameter("cantidad");
            if (!StringUtils.isBlank(cantidadProducto)) {
                cantidad = Integer.parseInt(cantidadProducto);
            }
            String precioTotalProducto = request.getParameter("precio");
            if (!StringUtils.isBlank(precioTotalProducto)) {
                precioTotal = Double.parseDouble(precioTotalProducto);
            }
            String pesoTotalProducto = request.getParameter("peso");
            if (!StringUtils.isBlank(pesoTotalProducto)) {
                pesoTotal = Double.parseDouble(pesoTotalProducto);
            }
            String nombre = request.getParameter("nombre");

            producto = new Producto();
            producto.setId(id);
            producto.setCantidad(cantidad);
            producto.setPrecio(precioTotal);
            producto.setNombre(nombre);
            producto.setPeso(pesoTotal);

            invoiceItemList.add(producto);

            PrintWriter out = response.getWriter();

            if (invoiceItemList.size() > 0) {

                out.println("<table class='table table-sm' style='font-size: 12px;'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th scope='col' style='width: 10%'>#</th>");
                out.println("<th scope='col' style='width: 40%'>Producto</th>");
                out.println("<th scope='col' class='text-right' style='padding-right: 20px; width: 10%'>Cantidad</th>");
                out.println("<th scope='col' class='text-right' style='padding-right: 20px; width: 10%'>Precio Total</th>");
                out.println("<th class='text-center' style='width:10%'>Action</th>");
                out.println("</tr>");
                out.println("</thead>");

                out.println("<tbody>");

                for (int i = 0; i < invoiceItemList.size(); i++) {
                    int num = i + 1;
                    totalInvoice += invoiceItemList.get(i).getCantidad() * invoiceItemList.get(i).getPrecio();
                    totalPeso += invoiceItemList.get(i).getCantidad() * invoiceItemList.get(i).getPeso();
                    out.println("<tr>");
                    out.println(" <th scope='row'>" + num + "</th>");
                    out.println("<td>" + invoiceItemList.get(i).getNombre() + "</td>");
                    out.println("<td class='text-end pr-3' style='padding-right: 20px'>" + invoiceItemList.get(i).getCantidad() + "</td>");
                    out.println("<td class='text-end pr-3' style='padding-right: 20px'>" + decimalFormat.format(invoiceItemList.get(i).getPrecio()) + "</td>");
                    out.println("<td class='text-center'><form>"
                            + "<input type='hidden' name='item' id='item' value='" + i + "'>"
                            + "<input class='btn btn-danger btn-sm' type='button' value='Delete' id='btnDelete' onclick='deleteItem(" + i + " );'>"
                            + "</form>"
                            + "</td>");
                    out.println("</tr>");
                    out.println("</tr>");
                }
                out.println("</tbody>");

                String totalInvoiceString = decimalFormat.format(totalInvoice);

                out.println("<tfoot>");
                out.println("<tr>");
                out.println("<td class='text-end' colspan='3'>Total: </td>");
                out.println("<td class='text-end' style='padding-right: 20px'><strong>$" + totalInvoiceString + "</strong</td>");
                out.println("<td></td>");
                out.println("</tr>");
                out.println("</tfoot>");
                out.println("</table>");
                out.println("<input type='hidden' id='pesoTotalProducto' value='" + totalPeso + "'>");

            }

        } catch (Exception Ex) {

            Ex.printStackTrace();
            System.out.println(Ex.getMessage());
        }

    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) {

        String pattern = "#,###,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setGroupingSize(3);

        HttpSession session = request.getSession(true);
        List<Producto> invoiceItemList = (List<Producto>) session.getAttribute("InvoiceItems");
        Producto producto = null;
        double totalInvoice = 0;
        double totalPeso = 0;

        if (invoiceItemList == null) {
            invoiceItemList = new ArrayList<Producto>();
            session.setAttribute("InvoiceItems", invoiceItemList);
        }

        try {

            int item = Integer.parseInt(request.getParameter("item"));

            invoiceItemList.remove(item);

            PrintWriter out = response.getWriter();

            if (invoiceItemList.size() > 0) {

                out.println("<table class='table table-sm' style='font-size: 12px;'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th scope='col' style='width: 10%'>#</th>");
                out.println("<th scope='col' style='width: 40%'>Producto</th>");
                out.println("<th scope='col' class='text-right' style='padding-right: 20px; width: 10%'>Cantidad</th>");
                out.println("<th scope='col' class='text-right' style='padding-right: 20px; width: 10%'>Precio Total</th>");
                out.println("<th class='text-center' style='width:10%'>Action</th>");
                out.println("</tr>");
                out.println("</thead>");

                out.println("<tbody>");

                for (int i = 0; i < invoiceItemList.size(); i++) {
                    int num = i + 1;
                    totalInvoice += invoiceItemList.get(i).getCantidad() * invoiceItemList.get(i).getPrecio();
                    totalPeso += invoiceItemList.get(i).getCantidad() * invoiceItemList.get(i).getPeso();
                    out.println("<tr>");
                    out.println(" <th scope='row'>" + num + "</th>");
                    out.println("<td>" + invoiceItemList.get(i).getNombre() + "</td>");
                    out.println("<td class='text-end pr-3' style='padding-right: 20px'>" + invoiceItemList.get(i).getCantidad() + "</td>");
                    out.println("<td class='text-end pr-3' style='padding-right: 20px'>" + decimalFormat.format(invoiceItemList.get(i).getPrecio()) + "</td>");
                    out.println("<td class='text-center'><form>"
                            + "<input type='hidden' name='item' id='item' value='" + i + "'>"
                            + "<input class='btn btn-danger btn-sm' type='button' value='Delete' id='btnDelete' onclick='deleteItem(" + i + " );'>"
                            + "</form>"
                            + "</td>");
                    out.println("</tr>");
                    out.println("</tr>");
                }
                out.println("</tbody>");

                String totalInvoiceString = decimalFormat.format(totalInvoice);

                out.println("<tfoot>");
                out.println("<tr>");
                out.println("<td class='text-end' colspan='3'>Total: </td>");
                out.println("<td class='text-end' style='padding-right: 20px'><strong>$" + totalInvoiceString + "</strong</td>");
                out.println("<td></td>");
                out.println("</tr>");
                out.println("</tfoot>");
                out.println("</table>");
                out.println("<input type='hidden' id='pesoTotalProducto' value='" + totalPeso + "'>");

            } else {
                out.println("<input type='hidden' id='pesoTotalProducto' value='0'>");
                session.removeAttribute("InvoiceItems");
                session = request.getSession(false);
            }

        } catch (Exception Ex) {

            Ex.printStackTrace();
            System.out.println(Ex.getMessage());
        }

    }

}
