package test;

import managers.Managers;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;


class InMemoryTaskManagerTest extends TaskManagerTest {

    @BeforeEach
    public void beforeEach() throws IOException, InterruptedException {
        taskManager = Managers.getDefault();
    }


}