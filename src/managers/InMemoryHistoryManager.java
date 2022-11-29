package managers;

import tasks.*;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    List<Task> history = new LinkedList<>();
    Map<Integer, Node> historyMap = new HashMap<>();
    private Node tail;
    private Node head;


    @Override
    public void add(Task task) {
        if (historyMap.containsKey(task.getId())) {
            removeNode(historyMap.get(task.getId()));
            historyMap.remove(task.getId());
        }
        linkLast(task);


    }

    @Override
    public List<Task> getHistory() {
        return history;
    }


    @Override
    public void remove(int id) {
        removeNode(historyMap.get(id));
        historyMap.remove(id);
    }


    public void linkLast(Task task) {
        final Node oldTail = tail;
        final Node newNode = new Node(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) head = newNode;
        else oldTail.next = newNode;
        historyMap.put(task.getId(), newNode);
    }

    public ArrayList<Task> getTasks() {
        if (historyMap.size() > 0) {
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(head.data);
            Node node = head;
            while (node.next != null) {
                node = node.next;
                tasks.add(node.data);
            }
            return tasks;
        } else return new ArrayList<>();
    }

    public void removeNode(Node node) {
        if (node.next != null) {
            node.next.prev = node.prev;
        } else tail = node.prev;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else head = node.next;


    }


    static class Node {

        public Task data;
        public Node next;
        public Node prev;

        public Node(Task data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(Node prev, Task data, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

    }


}
