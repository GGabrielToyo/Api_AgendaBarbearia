package api_bravos.barber.domain.agendamentos;

import api_bravos.barber.domain.barbeiro.Barbeiro;
import api_bravos.barber.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "agendamentos")
@Entity(name = "Agendamento")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public void cancelar(MotivoCancelamento motivo){
        this.motivoCancelamento = motivo;
    }

    Agendamento(Long id, Barbeiro barbeiro, Usuario usuario, LocalDateTime data, MotivoCancelamento motivo) {
        this.id = id;
        this.barbeiro = barbeiro;
        this.usuario = usuario;
        this.data = data;
        this.motivoCancelamento = motivo;
    }

}
