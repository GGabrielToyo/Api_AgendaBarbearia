package api_bravos.barber.domain.usuario;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosEditarUsuario(
        @NotNull
        Long id,
        String nome,
        LocalDateTime nascimento,
        String telefone,
        String login,
        String senha
) {
}
