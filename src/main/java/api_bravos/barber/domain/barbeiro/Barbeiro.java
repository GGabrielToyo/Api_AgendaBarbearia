package api_bravos.barber.domain.barbeiro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "barbeiros")
@Entity(name = "Barbeiro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Barbeiro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private boolean ativo;

    public Barbeiro(DadosCadastroBarbeiro dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(DadosEditarBarbeiro dados) {
        if (dados.nome() != null && dados.nome() != this.nome) {
            this.nome = dados.nome();
        }
        if (dados.email() != null && dados.email() != this.email) {
            this.email = dados.email();
        }
    }
}
