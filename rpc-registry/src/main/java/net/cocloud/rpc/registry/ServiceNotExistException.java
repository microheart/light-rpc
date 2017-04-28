package net.cocloud.rpc.registry;

/**
 * service if not exist in service register center, will throw this exception.
 */
public class ServiceNotExistException extends RuntimeException {

    public ServiceNotExistException() {
        super();
    }

    public ServiceNotExistException(String message) {
        super(message);
    }

    public ServiceNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceNotExistException(Throwable cause) {
        super(cause);
    }
}
