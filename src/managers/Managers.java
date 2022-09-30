package managers;

public class Managers {
    TaskManager manager;
    static InMemoryHistoryManager historyManager;


    TaskManager getDefault() {
        return manager;
    }

    static HistoryManager getDefaultHistory() {
        return historyManager;
    }
}
