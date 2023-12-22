package api_bravos.barber.domain.agendamentos.validacoes.agendamento;

import api_bravos.barber.domain.ValidacaoException;
import api_bravos.barber.domain.agendamentos.DadosAgendamento;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedencia")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento{
    public void validar(DadosAgendamento dados) {
        var dataAgendamento = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataAgendamento).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Agendamento deve ser marcado com 30 minutos de antecedência!");
        }
    }
}
