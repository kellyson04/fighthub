---
title: "Sistema de Gerenciamento de Alunos de Boxe"
aliases: ["Sistema de Gerenciamento de Alunos de Boxe"]
tags: ["projeto", "requisitos", "modelagem", "mentoria"]
created: 2026-07-21
updated: 2026-07-21
status: active
domain: "projetos-de-pratica"
type: "projeto-pratico"
---

# Sistema de Gerenciamento de Alunos de Boxe

## Visão Geral

sistema simples para cadastrar alunos de boxe, controlar turmas, registrar presença, acompanhar categoria, faixa de peso, evolução técnica e situação financeira.

## Jornada 1: Cadastro de Aluno
```
Aluno procurou a academia de boxe
↓
Atendente informou nome, telefone, data de nascimento, peso, objetivo e plano desejado
↓
Sistema validou dados obrigatórios
↓
Sistema classificou faixa etária e faixa de peso inicial
↓
Sistema criou matrícula
↓
Aluno ficou ativo no sistema
```

## Jornada 2: Definição de Categoria e Perfil de Treino
```
Instrutor selecionou um aluno ativo
↓
Instrutor informou nível técnico, objetivo e experiência prévia
↓
Sistema sugeriu categoria de treino
↓
Instrutor confirmou categoria, faixa de peso e restrições físicas
↓
Perfil de treino foi salvo
↓
Aluno ficou disponível para alocação em turmas compatíveis
```

## Jornada 3: Gerenciamento de Turmas
```
Coordenador criou uma turma de boxe
↓
Coordenador definiu horário, instrutor, limite de alunos e nível da turma
↓
Sistema validou conflito de horário do instrutor
↓
Sistema liberou turma para matrículas
↓
Aluno foi vinculado a uma turma compatível
↓
Lista de alunos da turma foi atualizada
```

## Jornada 4: Registro de Presença
```
Aluno chegou para a aula
↓
Atendente ou instrutor informou matrícula do aluno
↓
Sistema verificou se o aluno estava ativo e vinculado à turma
↓
Sistema registrou presença na aula
↓
Histórico de frequência foi atualizado
↓
Instrutor pôde consultar assiduidade do aluno
```

## Jornada 5: Controle de Faixa de Peso
```
Instrutor registrou nova pesagem do aluno
↓
Sistema salvou peso, data e responsável pela medição
↓
Sistema comparou peso atual com faixa cadastrada
↓
Sistema identificou manutenção ou mudança de faixa de peso
↓
Histórico de pesagens foi atualizado
↓
Categoria competitiva do aluno pôde ser revisada
```

## Jornada 6: Evolução Técnica do Aluno
```
Instrutor avaliou desempenho do aluno
↓
Instrutor registrou golpes, defesa, movimentação, condicionamento e observações
↓
Sistema validou os campos de avaliação
↓
Sistema salvou evolução técnica
↓
Histórico de avaliações foi atualizado
↓
Instrutor pôde ajustar o plano de treino do aluno
```

## Jornada 7: Liberação para Sparring
```
Instrutor selecionou um aluno para sparring
↓
Sistema verificou nível técnico, presença mínima e restrições físicas
↓
Instrutor confirmou uso de equipamentos obrigatórios
↓
Sistema registrou autorização para sparring
↓
Aluno foi liberado para participar da atividade
↓
Histórico de sparring foi atualizado
```

## Jornada 8: Pagamento de Mensalidade
```
Aluno consultou mensalidade em aberto
↓
Sistema exibiu valor, vencimento e plano contratado
↓
Pagamento foi registrado
↓
Sistema atualizou status da mensalidade
↓
Matrícula permaneceu ativa
↓
Aluno continuou liberado para aulas e atividades permitidas
```

## Jornada 9: Bloqueio por Pendência
```
Sistema verificou mensalidades vencidas ou documentação pendente
↓
Sistema identificou bloqueio aplicável
↓
Matrícula foi marcada como bloqueada
↓
Aluno tentou registrar presença
↓
Sistema negou check-in e exibiu motivo
↓
Atendente pôde orientar regularização
```

## Jornada 10: Encerramento ou Pausa da Matrícula
```
Aluno solicitou pausa ou encerramento da matrícula
↓
Atendente consultou situação financeira e turmas vinculadas
↓
Sistema validou pendências em aberto
↓
Matrícula foi pausada ou encerrada
↓
Vínculos com turmas foram atualizados
↓
Histórico do aluno permaneceu disponível para consulta
```

## Relacionado

- [[Arquitetura]]
- [[Context Mapping]]
- [[Domain Discovery]]
- [[Event Storming]]
- [[Histórias de Usuário]]
- [[Modelagem de Domínio]]
- [[Requisitos]]
