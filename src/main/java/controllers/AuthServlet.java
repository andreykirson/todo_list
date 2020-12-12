package controllers;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Andrey
 * @date 10/12/2020
 */


public class AuthServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("AuthServlet's doPOST() called");
        User user = store.findUserByEmail(req.getParameter("email"));
        LOG.debug("Found user: {}", user);
        String email = req.getParameter("email");
        LOG.debug("Get email from user: {}", email);
        String password = req.getParameter("password");
        LOG.debug("Get password from user: {}", password);
        if (user != null && Objects.equals(user.getEmail(), email) && Objects.equals(user.getPassword(), password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            LOG.debug("User {} is logged in: ", user.toString());
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            LOG.error("You entered wrong password or email");
            req.setAttribute("error", "User not found, pls check your password or email");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}