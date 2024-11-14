package com.followcargo.transporte.controlador;

import com.followcargo.transporte.dao.Conductor;
import com.followcargo.transporte.modelo.ModeloConductores;
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

@WebServlet(name = "ControladorCondutores", urlPatterns = {"/Condutores", "/condutores"})
public class ControladorConductores extends HttpServlet {

    private ModeloConductores modeloConductores;

    @Resource(name = "jdbc/FollowCargo")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        modeloConductores = new ModeloConductores(dataSource);

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
                getListaConductores(request, response);
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
                getListaConductores(request, response);
                break;

            case "get":
                getConductor(request, response);
                break;

            case "add":
                addConductor(request, response);
                break;

        }

    }

    private void getListaConductores(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

        List<Conductor> listaConductores = new ArrayList<>();

        try {

            listaConductores = modeloConductores.getListaConductores();

            String json = new Gson().toJson(listaConductores);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void getConductor(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

        Conductor conductor = null;

        try {

            String id = request.getParameter("id");

            conductor = modeloConductores.getConductor(id);

            String json = new Gson().toJson(conductor);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addConductor(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos

        Conductor conductor = null;

        boolean flag = false;

        Gson gson = new Gson();

        try {

            InputStream in = new BufferedInputStream(request.getInputStream());

            String result = IOUtils.toString(in, "UTF-8");

            conductor = gson.fromJson(result, Conductor.class);

            String usuario = request.getParameter("usuario");

            flag = modeloConductores.addConductor(conductor, usuario);

            String json = new Gson().toJson(flag);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
