package controllers;

import store.HbmStore;
import store.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {

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
           String[] str = fullLine.toString().split("\\s+");
            int id = Integer.parseInt(str[0]);
            boolean status = Boolean.parseBoolean(str[1]);
            store.setStatus(id, status);
            System.out.println(str[0]);
            System.out.println(str[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
