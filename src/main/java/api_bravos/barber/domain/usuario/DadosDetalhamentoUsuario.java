package api_bravos.barber.domain.usuario;

import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String nome,
        String telefone,
        LocalDateTime nascimento) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getNome(), usuario.getTelefone(), usuario.getNascimento());
    }

}
