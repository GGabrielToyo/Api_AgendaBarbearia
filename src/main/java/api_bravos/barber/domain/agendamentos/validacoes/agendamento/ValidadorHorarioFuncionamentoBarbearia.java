package api_bravos.barber.domain.agendamentos.validacoes.agendamento;

import api_bravos.barber.domain.ValidacaoException;
import api_bravos.barber.domain.agendamentos.DadosAgendamento;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component("ValidadorHorarioFuncionamentoBarbearia")
public class ValidadorHorarioFuncionamentoBarbearia implements ValidadorAgendamento{

    public void validar(DadosAgendamento dados){
        var dataAgendamento = dados.data();

        var domingo = dataAgendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaBarbearia = dataAgendamento.getHour() < 9;
        var depoisDaAberturaDaBarbearia = dataAgendamento.getHour() > 18;

        if (domingo || antesDaAberturaDaBarbearia || depoisDaAberturaDaBarbearia) {
            throw new ValidacaoException("Agendamento fora do horário de funcionamento da barbearia!");
        }
    }
}
