package org.example.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.WorkerController;
import org.example.model.Worker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/workers")
public class GetWorkersServlet extends HttpServlet {

    private final WorkerController controller = new WorkerController();
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException{

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        String json = sb.toString();



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
}
