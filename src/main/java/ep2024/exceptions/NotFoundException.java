package ep2024.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("----------The item with id: " + id.toString() + " has not been found.");
    }
}
