package api_bravos.barber.domain.agendamentos.validacoes.agendamento;

import api_bravos.barber.domain.agendamentos.AgendamentoRepository;
import api_bravos.barber.domain.agendamentos.DadosAgendamento;
import api_bravos.barber.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorBarbeiroComOutroAgendamentoNoMesmoHorario")
public class ValidadorBarbeiroComOutroAgendamentoNoMesmoHorario implements  ValidadorAgendamento{

    @Autowired
    private AgendamentoRepository repositoryAgendamento;

    public void validar(DadosAgendamento dados){
        var barbeiroPossuiAgendamentoNoHorario = repositoryAgendamento.existsByBarbeiroIdAndDataAndMotivoCancelamentoIsNull(dados.idBarbeiro(), dados.data());

        if(barbeiroPossuiAgendamentoNoHorario){
            throw new ValidacaoException("Barbeiro já possui agendamento neste horário!");
        }
    }
}
