package test;

import managers.KVServer;
import managers.Managers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;


class InMemoryTaskManagerTest extends TaskManagerTest {

    static KVServer kvServer;
    @BeforeAll
    static void beforeAll() throws IOException {
        kvServer= new KVServer();
        kvServer.start();
    }

    @AfterAll
    static void afterAll() {
        kvServer.stop();
    }

    @BeforeEach
    public void beforeEach() throws IOException, InterruptedException {
        taskManager = Managers.getDefault();
    }


}