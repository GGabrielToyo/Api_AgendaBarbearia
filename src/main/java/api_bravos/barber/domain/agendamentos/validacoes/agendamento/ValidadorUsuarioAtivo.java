package api_bravos.barber.domain.agendamentos.validacoes.agendamento;

import api_bravos.barber.domain.ValidacaoException;
import api_bravos.barber.domain.agendamentos.DadosAgendamento;
import api_bravos.barber.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorUsuarioAtivo")
public class ValidadorUsuarioAtivo implements  ValidadorAgendamento{

    @Autowired
    private UsuarioRepository repositoryUsuario;

    public void validar(DadosAgendamento dados){
        var usuarioAtivo = repositoryUsuario.findAtivoById(dados.idUsuario());

        if(!usuarioAtivo){
            throw new ValidacaoException("Agendamento não pode ser feito com usuário inativo!");
        }
    }

}
