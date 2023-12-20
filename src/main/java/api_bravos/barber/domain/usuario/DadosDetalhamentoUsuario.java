package api_bravos.barber.domain.usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String nome,
        String telefone) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getNome(), usuario.getTelefone());
    }
}
