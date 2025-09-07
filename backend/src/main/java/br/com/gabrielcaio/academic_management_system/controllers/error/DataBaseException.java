package br.com.gabrielcaio.academic_management_system.controllers.error;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String message) {
        super(message);
    }
}
