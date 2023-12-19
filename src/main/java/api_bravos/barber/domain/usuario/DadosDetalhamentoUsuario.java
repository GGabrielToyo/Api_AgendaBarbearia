package api_bravos.barber.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String login) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin());
    }
}
