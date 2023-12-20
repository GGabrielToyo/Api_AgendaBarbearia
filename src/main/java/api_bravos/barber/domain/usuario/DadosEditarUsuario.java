package api_bravos.barber.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosEditarUsuario(
        @NotNull
        Long id,
        String nome,
        String telefone
) {
}
