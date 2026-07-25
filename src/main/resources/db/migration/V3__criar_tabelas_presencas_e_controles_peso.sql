CREATE TABLE tb_presencas (
    id BIGSERIAL PRIMARY KEY,
    matricula_id BIGINT NOT NULL,
    data_presenca DATE NOT NULL,
    CONSTRAINT fk_presenca_matricula
        FOREIGN KEY (matricula_id) REFERENCES tb_matriculas_turma (id)
);

CREATE TABLE tb_controles_peso (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    peso NUMERIC(5, 2) NOT NULL,
    data_da_medicao DATE NOT NULL,
    responsavel_pela_medicao VARCHAR(100) NOT NULL,
    CONSTRAINT fk_controle_peso_aluno
        FOREIGN KEY (aluno_id) REFERENCES tb_alunos (id)
);
