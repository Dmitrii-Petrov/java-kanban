package managers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

import java.io.IOException;
import java.nio.file.Path;

public class HttpTaskServer {
    HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
    TaskManager fileBackedTasksManager = Managers.getFileBackedTaskManager(Path.of("./resources/1.csv"));


    public HttpTaskServer() throws IOException {
    }
}
