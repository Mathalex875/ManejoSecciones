/*
 * Nombre: Alexis González
 * Fecha: 11/11/2025
 * Descripción:
 * Esta interfaz define el servicio de autenticación para obtener el nombre
 * de usuario asociado a una sesión HTTP.
 * Su implementación permite verificar si un usuario está logueado
 * y recuperar su información desde la solicitud (HttpServletRequest).
 */

package services;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface LoginService {

    /*
     * Método: getUsername
     * Parámetro: HttpServletRequest request - Permite acceder a los datos de la solicitud HTTP.
     * Retorna: Optional<String> - Contiene el nombre de usuario si existe una sesión activa,
     *                              o un Optional vacío si el usuario no ha iniciado sesión.
     * Descripción:
     * Este método se utiliza para obtener el nombre del usuario logueado
     * a partir de la información de sesión almacenada en la petición HTTP.
     */
    Optional<String> getUsername(HttpServletRequest request);
}
