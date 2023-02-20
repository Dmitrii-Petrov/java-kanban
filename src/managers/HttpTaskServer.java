package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static tasks.TaskStatus.*;


public class HttpTaskServer {

    public static final int PORT = 8080;
    HttpServer server;
    Gson gson;

    TaskManager taskManager;


    public HttpTaskServer() throws IOException {
        this.taskManager = Managers.getFileBackedTaskManager(Path.of("./resources/test.csv"));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/tasks", this::handleTasks);
    }

    private void handleTasks(HttpExchange httpExchange) {
        try {
            String path = httpExchange.getRequestURI().getPath();
            String requestMethod = httpExchange.getRequestMethod();
            String[] pathSplit = path.split("/");
            switch (requestMethod) {
                case "GET": {
                    if (Pattern.matches("^/tasks/+$", path)) {

                    } else switch (pathSplit[2]) {
                        case "task": {
                            if (Pattern.matches("^/tasks/task/?id\\d+$", path)) {
                                break;
                            } else if (Pattern.matches("^/tasks/task/+$", path)) {
                                break;
                            } else {
                                System.out.println("неверный path");
                                httpExchange.sendResponseHeaders(405, 0);
                                break;
                            }
                        }
                        case "epic": {
                            if (Pattern.matches("^/tasks/epic/?id\\d+$", path)) {
                                break;
                            } else if (Pattern.matches("^/tasks/epic/+$", path)) {
                                break;
                            } else {
                                System.out.println("неверный path");
                                httpExchange.sendResponseHeaders(405, 0);
                                break;
                            }
                        }
                        case "subtask": {
                            if (Pattern.matches("^/tasks/subtask/epic/?id\\d+$", path)) {

                                break;
                            } else {
                                break;
                            }
                        }
                        case "history": {
                            break;
                        }
                        default: {
                            System.out.println("неверный path");
                            httpExchange.sendResponseHeaders(405, 0);
                        }
                    }

                    break;
                }
                case "POST": {
                    break;
                }
                case "DELETE": {


                    break;
                }
                default: {
                    System.out.println("Только методы GET, POST, DELETE. Получили - " + requestMethod);
                    httpExchange.sendResponseHeaders(405, 0);
                }

            }


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            httpExchange.close();
        }


    }

    public void start() {
        System.out.println("Started TaskServer " + PORT);
        server.start();
    }

    public void stop() {
        server.stop(0);
        System.out.println("Stopped TaskServer " + PORT);
    }

    public static void main(String[] args) throws IOException {
        HttpTaskServer httpTaskServer = new HttpTaskServer();
        httpTaskServer.start();
        httpTaskServer.stop();


    }

}
