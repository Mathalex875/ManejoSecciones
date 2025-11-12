package services;
/*
Descripcion:  Interfaz que define los métodos del servicio de autenticación (login).
 Permite obtener el nombre de usuario desde una solicitud HTTP.
Autor: Alexis González
Fecha: 2025/11/12
 */
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface LoginService {

    /*
     * Obtiene el nombre de usuario almacenado en la sesión HTTP.
     * @param req Objeto HttpServletRequest que contiene la sesión activa.
     * @return Un Optional con el nombre de usuario si existe, o vacío si no hay sesión válida.
     */
    Optional<String> getUsername(HttpServletRequest req);
}
