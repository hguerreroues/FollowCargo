package com.followcargo.transporte.controlador;

import com.followcargo.transporte.dao.Vehiculo;
import com.followcargo.transporte.modelo.ModeloVehiculos;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ControlladorVehiculo", urlPatterns = {"/Vehiculo", "/vehiculo"})
public class ControlladorVehiculo extends HttpServlet {

    private ModeloVehiculos modeloVehiculo;

    @Resource(name = "jdbc/FollowCargo")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        modeloVehiculo = new ModeloVehiculos(dataSource);
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
                getListaVehiculo(request, response);
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
                getListaVehiculo(request, response);
                break;

            case "get":
                getVehiculo(request, response);
                break;

            case "add":
                addVehiculo(request, response);
                break;

        }
    }

    private void getListaVehiculo(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

        List<Vehiculo> listaVehiculos = new ArrayList<>();

        try {

            listaVehiculos = modeloVehiculo.getListaVehiculos();

            String json = new Gson().toJson(listaVehiculos);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void getVehiculo(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

        Vehiculo vehiculo = null;

        try {

            String id = request.getParameter("id");

            vehiculo = modeloVehiculo.getVehiculo(id);

            String json = new Gson().toJson(vehiculo);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addVehiculo(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

        Vehiculo vehiculo = null;
        
        boolean flag = false;

        try {

            String tipo = request.getParameter("tipo");
            String marca = request.getParameter("marca");
            String modelo = request.getParameter("modelo");
            String placa = request.getParameter("placa");
            String username = request.getParameter("usuario");
            
            vehiculo = new Vehiculo(tipo, marca, modelo, placa);

            flag = modeloVehiculo.addVehiculo(vehiculo, username);

            String json = new Gson().toJson(flag);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
