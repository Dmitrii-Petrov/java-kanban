package managers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Arrays;


public class HttpTaskServer {
    HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
    static TaskManager fileBackedTasksManager = FileBackedTasksManager.loadFromFile(Path.of("./resources/test2.csv"));


    static class TasksHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String method = httpExchange.getRequestMethod();
            URI requestURI = httpExchange.getRequestURI();
            String path = requestURI.getPath();
            String[] splitStrings = path.split("/");


            InputStream inputStream = httpExchange.getRequestBody();
            String body = new String(inputStream.readAllBytes());


            System.out.println(splitStrings[2]);
            //System.out.println("Началась обработка " + method + " /tasks запроса от клиента.");

            String response;
            switch (splitStrings[2]) {
                case "task":
                    switch (method) {
                        case "POST":
                            response = fileBackedTasksManager.getTask(1).toString();
                            break;
                        case "GET":
                            response = "Вы использовали метод GET!";
                            break;
                        case "DELETE":
                            response = "Вы использовали метод DELETE!";
                            fileBackedTasksManager.deleteTask(1);
                            break;

                        default:
                            response = "Вы использовали какой-то другой метод!";
                    }
                    break;
                default:
                    response = "не туда!";
            }

            httpExchange.sendResponseHeaders(200, 0);

            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    public HttpTaskServer() throws IOException {
    }


    public static void main(String[] args) throws IOException {
        HttpTaskServer httpTaskServer = new HttpTaskServer();
        httpTaskServer.httpServer.createContext("/tasks", new TasksHandler());
        httpTaskServer.httpServer.start();


    }

}
