
package com.followcargo.transporte.controlador;

import com.followcargo.transporte.dao.Producto;
import com.followcargo.transporte.modelo.ModeloProductos;
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

@WebServlet(name = "ControladorProducto", urlPatterns = {"/Producto", "/producto"})
public class ControladorProducto extends HttpServlet {
    
    private ModeloProductos modeloProductos;

    @Resource(name = "jdbc/FollowCargo")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        modeloProductos = new ModeloProductos(dataSource);
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
                getListaProductos(request, response);
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
                getListaProductos(request, response);
                break;

            case "get":
                getProducto(request, response);
                break;

            case "add":
                addProducto(request, response);
                break;

        }
    }

    private void getListaProductos(HttpServletRequest request, HttpServletResponse response) {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos


        List<Producto> listaProductos = new ArrayList<>();

        try {

            listaProductos = modeloProductos.getListaProductos();

            String json = new Gson().toJson(listaProductos);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void getProducto(HttpServletRequest request, HttpServletResponse response) {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Producto producto = null;

        try {

            String id = request.getParameter("id");

            producto = modeloProductos.getProducto(id);

            String json = new Gson().toJson(producto);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addProducto(HttpServletRequest request, HttpServletResponse response) {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Producto producto = null;
        
        boolean flag = false;
        
        Gson gson = new Gson();

        try {
            
            InputStream in = new BufferedInputStream(request.getInputStream());

            String result = IOUtils.toString(in, "UTF-8");

            producto = gson.fromJson(result, Producto.class);
            
            String usuario = request.getParameter("usuario");
            
            flag = modeloProductos.addProducto(producto, usuario);

            String json = new Gson().toJson(flag);

            response.getWriter().write(json);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
