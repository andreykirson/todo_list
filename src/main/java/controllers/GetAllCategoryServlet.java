package controllers;

import com.google.gson.Gson;
import model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * @author Andrey
 * @date 10/12/2020
 */


public class GetAllCategoryServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(GetAllCategoryServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("GetAllCategory's doGET() called");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Collection<Category> categories = store.findAllCategories();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String json = new Gson().toJson(categories);
        writer.println(json);
        writer.flush();
        writer.close();
        LOG.debug("GetAllServlet finished");
    }
}
