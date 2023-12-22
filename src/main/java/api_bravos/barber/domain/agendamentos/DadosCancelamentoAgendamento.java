package api_bravos.barber.domain.agendamentos;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoAgendamento(
        @NotNull
        Long id,
        @NotNull
        MotivoCancelamento motivo
) {
}
