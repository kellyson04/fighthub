CREATE TABLE tb_alunos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(16) NOT NULL,
    data_de_nascimento DATE NOT NULL,
    peso NUMERIC(5, 2) NOT NULL,
    objetivo VARCHAR(30) NOT NULL,
    plano_desejado VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL
);

CREATE TABLE tb_perfis_treino (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL UNIQUE,
    nivel_tecnico VARCHAR(20) NOT NULL,
    experiencia_previa VARCHAR(500) NOT NULL,
    categoria_treino VARCHAR(30) NOT NULL,
    faixa_peso VARCHAR(30) NOT NULL,
    restricoes_fisicas TEXT,
    CONSTRAINT fk_perfil_treino_aluno
        FOREIGN KEY (aluno_id) REFERENCES tb_alunos (id)
);
