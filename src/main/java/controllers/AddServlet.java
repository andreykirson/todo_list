package controllers;

import com.google.gson.*;
import model.Item;
import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


public class AddServlet extends HttpServlet {

    private final Store store = HbmStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
