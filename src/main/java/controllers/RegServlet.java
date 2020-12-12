package controllers;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrey
 * @date 10/12/2020
 */


public class RegServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("RegServlet's doGET() called");
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("RegServlet's doPOST() called");
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("Name");
        System.out.println(name);
        String email = req.getParameter("Email Address");
        String password = req.getParameter("Password");
        User user = new User(name, email, password);
        System.out.println(user.toString());
        store.createUser(user);
        LOG.debug("User {} is added: ", user);
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
}