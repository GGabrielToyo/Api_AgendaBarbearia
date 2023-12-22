package api_bravos.barber.domain.agendamentos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamento(
        @NotNull
        Long idBarbeiro,
        @NotNull
        Long idUsuario,
        @NotNull
        @Future
        LocalDateTime data
) {
}
