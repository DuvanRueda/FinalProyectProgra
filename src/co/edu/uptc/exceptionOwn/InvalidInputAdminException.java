package co.edu.uptc.exceptionOwn;

public class InvalidInputAdminException extends Exception{

     private static final int MAX_LENGTH = 10;
     
     public InvalidInputAdminException(String input){
        super(generateMessage(input));
     }

     private static String generateMessage(String input) {
        if (input == null || input.isEmpty()) {
            return "Error: la entrada está vacía.";
        }

        if (!input.matches("^[a-zA-Z0-9]{1,10}$")) {
            return "Error: la entrada contiene caracteres no válidos. Solo se permiten letras y números.";
        }

        if (input.length() > MAX_LENGTH) {
            return "Error: la entrada excede el máximo de " + MAX_LENGTH + " caracteres (actual: " + input.length() + ").";
        }

        return "Error desconocido con la entrada.";
    }
}
