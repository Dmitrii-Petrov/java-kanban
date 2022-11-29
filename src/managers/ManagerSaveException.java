package managers;

import java.io.IOException;

public class ManagerSaveException extends IOException {
    public ManagerSaveException() {

    }

    public ManagerSaveException(final String message) {
        super(message);
    }
}
