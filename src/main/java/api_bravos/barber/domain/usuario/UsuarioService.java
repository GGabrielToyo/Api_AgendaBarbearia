package api_bravos.barber.domain.usuario;

import api_bravos.barber.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private TokenService tokenService;

    public String getLoginUsuario(String bearerToken){
        var token = bearerToken.replace("Bearer ", "");
        var subject = tokenService.getSubject(token);

        return subject;
    }
}
