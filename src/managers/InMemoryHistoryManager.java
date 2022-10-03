package managers;

import tasks.*;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    List<Task> history = new LinkedList<>();


    @Override
    public void add(Task task) {
        if (history.size() == 10) history.remove(0);
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}
