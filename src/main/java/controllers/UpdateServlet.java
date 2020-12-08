package controllers;

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

public class UpdateServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateServlet.class.getName());
    private final Store store = HbmStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("UpdateServletServlet's doPOST() called");
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
            Item item = store.findItemById(id);
            item.setDone(status);
            store.update(item);
            LOG.debug("The status of item is updated");
        } catch (IOException e) {
            LOG.error("Something goes wrong", e);
        }
    }
}
