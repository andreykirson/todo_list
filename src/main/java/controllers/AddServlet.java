package controllers;

import com.google.gson.*;
import model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("AddServlet's doPOST() called");
        req.setCharacterEncoding("UTF-8");
        try (BufferedReader reader = req.getReader()) {
            StringBuilder fullLine = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fullLine.append(line);
            }
            String pattern = "YYYY-MM-DD H:mm:ss";
            Gson gson = new GsonBuilder().setDateFormat(pattern).create();
            Item item = gson.fromJson(String.valueOf(fullLine), Item.class);
            store.addItem(item);
            LOG.debug("The item is added: {}", item);
        } catch (IOException e) {
            LOG.error("Something goes wrong", e);
        }
    }
}
