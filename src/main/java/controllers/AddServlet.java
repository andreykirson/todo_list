package controllers;

import com.google.gson.*;
import model.Category;
import model.Item;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class AddServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddServlet.class.getName());
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
            Object obj = new JSONParser().parse(String.valueOf(fullLine));
            JSONObject jo = (JSONObject) obj;

            Category category = new Category();
            category.setDesc((String) jo.get("desc"));

            Item item = new Item();
            item.setCategory(category);

            item.setDone((Boolean) jo.get("done"));
            String dateString = (String) jo.get("createdTime");
            String pattern = "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date createdDate = sdf.parse(dateString);

            User user = (User) req.getSession().getAttribute("user");

            System.out.println(user.getId());

            item.setUser(user);
            item.setCreatedTime(createdDate);
            item.setDescription((String) jo.get("description"));

            System.out.println(item.toString());

            store.addItem(item);
            LOG.debug("User : {}, added item {}", user.getUsername(), item.getDescription());



//            Gson gson = new GsonBuilder().setDateFormat(pattern).create();
//            Item item = gson.fromJson(String.valueOf(fullLine), Item.class);
//            System.out.println(item.toString());
//            Category category = gson.fromJson(String.valueOf(fullLine), Category.class);
//            System.out.println(category.toString());
//            item.setCategory(category);
//            User user = (User) req.getSession().getAttribute("user");
//            item.setUser(user);
//            store.addItem(item);
//            LOG.debug("User : {}, added item {}", user.getUsername(), item.getDescription());
        } catch (IOException | ParseException | java.text.ParseException e) {
            LOG.error("Something goes wrong", e);
        }
    }
}
