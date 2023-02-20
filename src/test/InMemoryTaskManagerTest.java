package test;

import managers.Managers;
import org.junit.jupiter.api.BeforeEach;


class InMemoryTaskManagerTest extends TaskManagerTest {

    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
    }


}