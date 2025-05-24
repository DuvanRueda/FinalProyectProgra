package co.edu.uptc.model;
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 20/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */

import co.edu.uptc.exceptionOwn.InvalidInputAdminException;

public class AdminCredentials {

    private final String ADMIN_REGEX = "^[A-Za-z0-9]{1,10}$";
    private String name;
    private String password;

    public AdminCredentials() {
        name = "Admin";
        password = "soyElAdmin231";
    }

    public boolean isValid(String name, String password) {
        return this.name.equals(name) && this.password.equals(password);
    }

    public String changeAdminName(String newName) {
        try {
            if (!newName.matches(ADMIN_REGEX))
                throw new InvalidInputAdminException(newName);
            return "Se ha cambiado la contraseña correctamente";
        } catch (InvalidInputAdminException e) {
            return e.getMessage();
        }
    }

    public String changeAdminPassword(String newPassword) {
        try {
            if (!newPassword.matches(ADMIN_REGEX))
                throw new InvalidInputAdminException(newPassword);
            return "Se ha cambiado la contraseña correctamente";
        } catch (InvalidInputAdminException e) {
            return e.getMessage();
        }
    }
}
