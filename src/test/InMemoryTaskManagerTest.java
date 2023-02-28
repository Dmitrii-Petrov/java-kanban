package test;

import managers.KVServer;
import managers.Managers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;


class InMemoryTaskManagerTest extends TaskManagerTest {

    @BeforeAll
    static void beforeAll() throws IOException {
        new KVServer().start();
    }
    @BeforeEach
    public void beforeEach() throws IOException, InterruptedException {
        taskManager = Managers.getDefault();
    }


}