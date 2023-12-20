package api_bravos.barber.domain.barbeiro;

import jakarta.validation.constraints.NotNull;

public record DadosEditarBarbeiro(
        @NotNull
        Long id,
        String nome,
        String email
) {
}
