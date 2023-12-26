package api_bravos.barber.domain.agendamentos.validacoes.cancelamento;

import api_bravos.barber.domain.agendamentos.AgendamentoRepository;
import api_bravos.barber.domain.agendamentos.DadosCancelamentoAgendamento;
import api_bravos.barber.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoAgendamento {

    @Autowired
    private AgendamentoRepository repositoryAgendamento;

    public void validar(DadosCancelamentoAgendamento dados){
        var agendamento = repositoryAgendamento.getReferenceById(dados.id());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, agendamento.getData()).toHours();

        if(diferencaEmHoras < 1) {
            throw new ValidacaoException("Agendamento só pode ser cancelado com 1 hora de antecedência!");
        }
    }
}
