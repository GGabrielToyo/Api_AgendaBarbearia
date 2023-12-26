package api_bravos.barber.domain.agendamentos;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(
    Long id,
    Long idBarbeiro,
    Long idUsuario,
    LocalDateTime data
) {
    public DadosDetalhamentoAgendamento(Agendamento agendamento){
        this(
                agendamento.getId(),
                agendamento.getBarbeiro().getId(),
                agendamento.getUsuario().getId(),
                agendamento.getData());
    }
}
