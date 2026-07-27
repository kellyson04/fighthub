CREATE TABLE tb_autorizacoes_sparring (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    instrutor VARCHAR(100) NOT NULL,
    data_do_sparring DATE NOT NULL,
    CONSTRAINT fk_autorizacao_sparring_aluno
        FOREIGN KEY (aluno_id) REFERENCES tb_alunos (id)
);
