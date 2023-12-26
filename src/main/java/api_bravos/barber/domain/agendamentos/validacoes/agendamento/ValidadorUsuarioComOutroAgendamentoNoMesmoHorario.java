package api_bravos.barber.domain.agendamentos.validacoes.agendamento;

import api_bravos.barber.domain.ValidacaoException;
import api_bravos.barber.domain.agendamentos.AgendamentoRepository;
import api_bravos.barber.domain.agendamentos.DadosAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorUsuarioComOutroAgendamentoNoMesmoHorario")
public class ValidadorUsuarioComOutroAgendamentoNoMesmoHorario implements ValidadorAgendamento{

    @Autowired
    private AgendamentoRepository repositoryAgendamento;

    @Override
    public void validar(DadosAgendamento dados) {
        var usuarioPossuiAgendamentoNoMesmoHorario = repositoryAgendamento.existsByUsuarioIdAndDataAndMotivoCancelamentoIsNull(dados.idUsuario(), dados.data());

        if(usuarioPossuiAgendamentoNoMesmoHorario){
            throw new ValidacaoException("Usuário já possui agendamento neste horário!");
        }

    }
}
