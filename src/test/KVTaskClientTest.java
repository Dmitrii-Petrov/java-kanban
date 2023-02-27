package test;

import managers.KVServer;
import managers.KVTaskClient;
import org.junit.jupiter.api.*;

import java.io.IOException;


class KVTaskClientTest {

    KVTaskClient client;

    @BeforeAll
    static void beforeAll() throws IOException {
        new KVServer().start();
    }

    @BeforeEach
    public void beforeEach() throws IOException, InterruptedException {
        client = new KVTaskClient("http://localhost:8078/");

    }

    @AfterEach
    public void afterEach() {

    }


    @Test
    void load() throws IOException, InterruptedException {
        client.put("test", "equal");
        client.put("test2", "not equal");
        Assertions.assertEquals("equal", client.load("test"));
        Assertions.assertNotEquals("equal", client.load("test2"));
    }
}