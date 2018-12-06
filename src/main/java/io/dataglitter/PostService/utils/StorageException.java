package io.dataglitter.PostService.utils;

/**
 * Created by reddys on 29/08/2018.
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}