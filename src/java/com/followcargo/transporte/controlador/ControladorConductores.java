package com.followcargo.transporte.controlador;

import com.followcargo.transporte.dao.Conductor;
import com.followcargo.transporte.modelo.ModeloConductores;
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

        }

    }

    private void getListaConductores(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

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

}
