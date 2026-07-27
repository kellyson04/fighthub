CREATE TABLE tb_avaliacoes_tecnicas (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    golpes TEXT NOT NULL,
    defesa TEXT NOT NULL,
    movimentacao TEXT NOT NULL,
    condicionamento TEXT NOT NULL,
    observacoes TEXT,
    data_da_avaliacao DATE NOT NULL,
    responsavel_pela_avaliacao VARCHAR(100) NOT NULL,
    CONSTRAINT fk_avaliacao_tecnica_aluno
        FOREIGN KEY (aluno_id) REFERENCES tb_alunos (id)
);
