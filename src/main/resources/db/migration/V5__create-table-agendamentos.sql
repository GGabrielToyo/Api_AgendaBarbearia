create table agendamentos(
    id bigint not null auto_increment,
    barbeiro_id bigint not null,
    usuario_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_agendamentos_barbeiro_id foreign key(barbeiro_id) references barbeiros(id),
    constraint fk_agendamentos_usuario_id foreign key(usuario_id) references usuarios(id)
);