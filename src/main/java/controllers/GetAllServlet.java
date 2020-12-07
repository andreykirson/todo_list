package controllers;

import com.google.gson.Gson;
import model.Item;
import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class GetAllServlet extends HttpServlet {

    private final Store store = HbmStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Collection<Item> items = store.findAll();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String json = new Gson().toJson(items);
        writer.println(json);
        writer.flush();
        writer.close();
    }
}
