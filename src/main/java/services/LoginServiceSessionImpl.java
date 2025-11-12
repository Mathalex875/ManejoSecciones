package services;
/*
Descripcion: Implementacion de LoginService que obtiene el nombre de usuario desde la sesion HTTP.
Almacena y recupera el nombre de usuario utilizando atributos de sesion.
Autor: Alexis González
Fecha: 2025/11/12
 */
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService {
    /*
     * Obtiene el nombre de usuario desde la sesión actual.
     * @param req getUsername HttpServletRequest con la sesión del usuario.
     * @return Un Optional con el nombre de usuario si existe, o vacío en caso contrario.
     */
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        //Se obtiene la sesion actual y se almacena
        HttpSession session = req.getSession();
        //Se recupera el atribuo 'username' de la sesion
        String username = (String) session.getAttribute("username") ;
        //si existe el nombre de usuario, se retorna envuelto en un Optional sino se retorna un Optional vacio
        if(username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
