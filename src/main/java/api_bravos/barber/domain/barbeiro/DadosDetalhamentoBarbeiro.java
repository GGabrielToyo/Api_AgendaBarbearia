package api_bravos.barber.domain.barbeiro;

public record DadosDetalhamentoBarbeiro(
        Long id,
        String nome,
        String email
) {
    public DadosDetalhamentoBarbeiro(Barbeiro barbeiro) {
        this(barbeiro.getId(), barbeiro.getNome(), barbeiro.getEmail());
    }
}
