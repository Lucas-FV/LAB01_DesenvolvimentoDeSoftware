# LAB01_Software De Matrícula



## Integrantes:
* Helio Ernesto Gouvea Dutra Teixeira
* Lucas Flor Vilela
* Renato Cazzoletti

## Orientador:
* Cleiton Silva Taveres

## Descrição do Sistema:

Uma universidade pretende informatizar seu sistema de matrículas. A secretaria da universidade gera o currículo para cada semestre e mantém as informações sobre as disciplinas, professores e alunos.

Cada curso tem um nome, um determinado número de créditos e é constituído por diversas disciplinas.

Os alunos podem se matricular em 4 disciplinas como 1ª opção (obrigatórias) e em mais 2 outras alternativas (optativas).

Há períodos para efetuar matrículas, durante os quais um aluno pode acessar o sistema para se matricular em disciplinas e/ou para cancelar matrículas feitas anteriormente.

Uma disciplina só fica ativa, isto é, só vai ocorrer no semestre seguinte se, no final do período de matrículas tiver, pelo menos, 3 alunos inscritos (matriculados). Caso contrário, a disciplina será cancelada. O número máximo de alunos inscritos a uma disciplina é de 60 e quando este número é atingido, as inscrições (matrículas) para essa disciplina são encerradas.

Após um aluno se inscrever para um semestre, o sistema de cobranças é notificado pelo sistema de matrículas, de modo que o aluno possa ser cobrado pelas disciplinas daquele semestre.

Os professores podem acessar o sistema para saber quais são os alunos que estão matriculados em cada disciplina.

Todos os usuários do sistema têm senhas que são utilizadas para validação do respectivo login.

## Descrição em histórias de usuário:

### 1. Secretaria

* História 1: Gerar Currículo

Como secretária da universidade, quero gerar o currículo de disciplinas para cada semestre, para disponibilizar as opções de matrícula para os alunos.

* Critérios de Aceitação:  

O currículo deve conter todas as disciplinas obrigatórias e optativas disponíveis.
O currículo deve estar acessível no sistema antes da abertura do período de matrículas.

### 2. Aluno

* História 2: Fazer Login

Como aluno, quero acessar o sistema com um login e senha válidos, para visualizar e gerenciar minhas matrículas.

* Critérios de Aceitação:  

O sistema deve validar as credenciais antes de permitir o acesso.
O sistema deve bloquear o acesso após 3 tentativas inválidas.

* História 3: Matricular-se em Disciplinas Obrigatórias

Como aluno, quero me matricular em até 4 disciplinas obrigatórias, para atender os requisitos do meu curso no semestre.

* Critérios de Aceitação:  

O sistema deve permitir a matrícula apenas durante o período estabelecido.
O sistema deve impedir a matrícula se a capacidade da disciplina estiver completa.

* História 4: Matricular-se em Disciplinas Optativas

Como aluno, quero me matricular em até 2 disciplinas optativas, para ampliar meu conhecimento e cumprir créditos extras.

* Critérios de Aceitação:  

O sistema deve permitir a matrícula apenas se o aluno já tiver feito a matrícula obrigatória.
O sistema deve bloquear a matrícula caso o número máximo de vagas tenha sido atingido.

* História 5: Cancelar Matrícula

Como aluno, quero cancelar minha matrícula em uma disciplina, para ajustar minha carga horária conforme minha disponibilidade.

* Critérios de Aceitação:  

O sistema deve permitir o cancelamento apenas durante o período de matrícula.
O sistema deve notificar o setor financeiro sobre a alteração.

### 3. Professor

* História 6: Consultar Matrículas

Como professor, quero visualizar a lista de alunos matriculados em minhas disciplinas, para planejar melhor minhas aulas e atividades.

* Critérios de Aceitação:  

O sistema deve exibir o nome, número de matrícula e curso dos alunos.
O acesso deve ser restrito apenas às disciplinas sob responsabilidade do professor.

### 4. Sistema Financeiro

* História 7: Gerar Cobrança

Como agente financeiro, quero vizualizar as informações de matrícula dos alunos, para gerar as cobranças correspondentes às disciplinas selecionadas

* Critérios de Aceitação:  

O sistema deve notificar o sistema financeiro imediatamente após a matrícula.
O valor deve ser calculado com base no número de disciplinas e créditos matriculados.

### Regras de Negócio Gerais

* O período de matrícula deve ter datas definidas pela secretaria e ser respeitado pelo sistema.
* Disciplinas com menos de 3 alunos no fim do período de matrícula devem ser automaticamente canceladas.
* Disciplinas com 60 alunos matriculados devem ser bloqueadas para novas inscrições.
* O sistema deve manter registros de matrículas, cancelamentos e cobranças.
* Todos os usuários devem ter credenciais únicas.

## Diagrama de casos de uso

![CasoDeUso LabDesenSoftware-01](https://github.com/user-attachments/assets/950a2f15-ca74-42d0-9b09-8c3e8879c609)



