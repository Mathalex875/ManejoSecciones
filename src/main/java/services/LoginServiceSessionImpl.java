/*
 * Nombre: Alexis González
 * Fecha: 11/11/2025
 * Descripción:
 * Esta clase implementa la interfaz LoginService y se encarga de gestionar
 * la autenticación de usuarios mediante el uso de sesiones HTTP.
 * Permite obtener el nombre del usuario actualmente logueado en la aplicación
 * a partir de la sesión activa, sin crear una nueva sesión en caso de no existir.
 */

package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService {

    /*
     * Método: getUsername
     * Parámetro: HttpServletRequest request - Permite acceder a la solicitud HTTP
     *                                         y recuperar la sesión del usuario.
     * Retorna: Optional<String> - Contiene el nombre de usuario si la sesión existe,
     *                              o un Optional vacío si no hay usuario autenticado.
     * Descripción:
     * Este método verifica si existe una sesión activa y si en ella se ha almacenado
     * un atributo llamado "username". Si el atributo existe, se devuelve su valor;
     * de lo contrario, se retorna un Optional vacío indicando que no hay sesión iniciada.
     */
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        // Obtener la sesión actual sin crear una nueva (false evita crear sesión vacía)
        HttpSession session = request.getSession(false);

        // Verificar si la sesión y el atributo "username" existen
        if (session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            return Optional.of(username); // Retorna el nombre del usuario
        }

        // Si no hay sesión o usuario, retorna vacío
        return Optional.empty();
    }
}
