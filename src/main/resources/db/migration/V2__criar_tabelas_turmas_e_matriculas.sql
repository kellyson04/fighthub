CREATE TABLE tb_turmas (
    id BIGSERIAL PRIMARY KEY,
    horario TIME NOT NULL,
    dia_da_semana VARCHAR(20) NOT NULL,
    instrutor VARCHAR(100) NOT NULL,
    limite_alunos INTEGER NOT NULL,
    nivel_turma VARCHAR(20) NOT NULL,
    aberta_para_matricula BOOLEAN NOT NULL
);

CREATE TABLE tb_matriculas_turma (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    turma_id BIGINT NOT NULL,
    CONSTRAINT uk_matricula_aluno_turma UNIQUE (aluno_id, turma_id),
    CONSTRAINT fk_matricula_aluno
        FOREIGN KEY (aluno_id) REFERENCES tb_alunos (id),
    CONSTRAINT fk_matricula_turma
        FOREIGN KEY (turma_id) REFERENCES tb_turmas (id)
);
