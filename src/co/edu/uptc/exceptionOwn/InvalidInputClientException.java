package co.edu.uptc.exceptionOwn;

public class InvalidInputClientException extends Exception{

    public InvalidInputClientException(String input){
        super(generateMessage(input));
     }

     private static String generateMessage(String input) {

        if (input == null || input.isEmpty()) {
            return "Error: la entrada está vacía.";
        }

        if (!input.matches("^[a-zA-Z0-9]+$")) {
            return "Error: la entrada contiene caracteres no válidos. Solo se permiten letras y números.(Ingleses, no tildes ni ñ)";
        }

        if (!input.matches("^(?=(?:[^A-Za-z]*[A-Za-z]){3,}).*$")) {
            return "Error: La entrada cuenta con menos de 3 letras.";
        }

        if (!input.matches("^(?=(?:[^0-9]*[0-9]){2,}).*$")) {
            return "Error: La entrada cuenta con menos de 2 números.";
        }

        return "Error desconocido con la entrada.";
    }
}
