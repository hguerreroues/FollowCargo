package com.followcargo.transporte.controlador;

import com.followcargo.transporte.dao.Usuario;
import com.followcargo.transporte.modelo.ModeloUsuario;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario, /usuario"})
public class ControladorUsuario extends HttpServlet {

    private ModeloUsuario modeloUsuario;

    @Resource(name = "jdbc/FollowCargo")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        modeloUsuario = new ModeloUsuario(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = ValidarString(request.getParameter("action"));
        if (action.equalsIgnoreCase("")) {
            action = "login";
        }

        switch (action) {

            case "login":
                getLogin(request, response);
                break;

            case "logout":
                getLogout(request, response);
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = ValidarString(request.getParameter("action"));
        if (action.equalsIgnoreCase("")) {
            action = "login";
        }

        switch (action) {

            case "login":
                getLogin(request, response);
                break;

        }
    }

    private String ValidarString(String string) {
        if (StringUtils.isBlank(string)) {
            string = "";
        }
        return string;
    }

    private void getLogin(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        String nombreUsuario = ValidarString(request.getParameter("nombreUsuario"));
        String contra = ValidarString(request.getParameter("contra"));

        try {

            System.out.println("nombreUsuario: " + nombreUsuario);
            System.out.println("contra: " + contra);
            boolean flag = modeloUsuario.getLogin(nombreUsuario, contra);
            System.out.println("flag: " + flag);

            if (flag) {

                Usuario usuario = modeloUsuario.getUsuario(nombreUsuario, contra);

                session.setAttribute("user_session", usuario);
                session.setAttribute("username", usuario.getNombreUsuario());
                request.setAttribute("usuario", usuario);

                dashboard(request, response);

            } else {

                session.setAttribute("login_message", "Usuario ó Contraseña incorrecta");
                response.sendRedirect("/FollowCargo");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
            session.setAttribute("login_message", ex.getMessage());
            System.out.println(ex.getMessage());

        }
    }

    private void getLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            //Cerrar sesion
            session.removeAttribute("user_session");
            session.setAttribute("login_message", "Cerrado la sesión exitosamente.");
            session.invalidate();

        }

        //Redirecciono a index.jsp
        response.sendRedirect("/FollowCargo");

    }

    private void dashboard(HttpServletRequest request, HttpServletResponse response) {

        Usuario usuario = null;
        //List<Vehiculo> vehiculo = new ArrayList<>();

        try {

            usuario = (Usuario) request.getAttribute("usuario");
            String nombreCompleto = usuario.getNombreCompleto();
            System.out.println(usuario);

            System.out.println("Servlet: ControladorUsuario?action=dashboard");

            request.setAttribute("nombreCompleto", nombreCompleto);
            request.setAttribute("menu", "main"); //Indicar al menu que es la pantalla principal para que amplie todas las opciones del menu.

            RequestDispatcher rDispatcher = request.getRequestDispatcher("/dashboard");
            rDispatcher.forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }

}
