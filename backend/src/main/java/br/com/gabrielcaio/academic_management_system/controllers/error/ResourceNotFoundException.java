package br.com.gabrielcaio.academic_management_system.controllers.error;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
