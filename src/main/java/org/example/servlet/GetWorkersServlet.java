package org.example.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.WorkerController;
import org.example.model.Worker;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/workers")
public class GetWorkersServlet extends HttpServlet {

    private final WorkerController controller = new WorkerController();
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getAllWorkers(resp);
    }

    private void getAllWorkers(HttpServletResponse resp) throws IOException{
        List<Worker> workerList = controller.getAll();

        resp.setContentType("application/json");

        try{
            String json = mapper.writeValueAsString(workerList);

            PrintWriter writer = resp.getWriter();
            writer.println(json);
            writer.close();

        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String getBody(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException{
        createWorker(req, response);
    }

    private void createWorker(HttpServletRequest req, HttpServletResponse response)throws IOException{
        String json = getBody(req);

        Worker worker = mapper.readValue(json, Worker.class);
        Worker save = controller.save(worker);
        response.setContentType("application/json");

        try{
            json = mapper.writeValueAsString(save);

            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.close();

        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse response) throws IOException {
        editWorker(req, response);
    }

    private void editWorker(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String json = getBody(req);

        Worker worker = mapper.readValue(json, Worker.class);
        Worker edit = controller.edit(worker);
        response.setContentType("application/json");

        try{
            json = mapper.writeValueAsString(edit);
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.close();

        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws IOException {
        deleteWorker(req, response);
    }

    private void deleteWorker(HttpServletRequest req, HttpServletResponse response) throws IOException {
        int id =Integer.parseInt(req.getParameter("id"));

        controller.delete(id);
        response.setContentType("application/json");

        try{
            PrintWriter writer = response.getWriter();
            writer.println("");
            writer.close();

        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
