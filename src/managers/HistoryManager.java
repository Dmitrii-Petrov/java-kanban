package managers;

import tasks.*;

import java.util.*;

public interface HistoryManager {

    List<Task> history = new ArrayList<>();

    void add(Task task);

    List<Task> getHistory();
}
