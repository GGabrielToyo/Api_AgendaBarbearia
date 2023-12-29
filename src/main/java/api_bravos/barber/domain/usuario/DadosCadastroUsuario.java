package api_bravos.barber.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosCadastroUsuario(

        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,

        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String login,

        @NotNull
        LocalDateTime nascimento,

        @NotNull
        String senha
) {
}
