# SISTEMA DE CLÍNICA MÉDICA

## 1. Descrição do problema

O projeto consiste no desenvolvimento de um sistema para gerenciamento de uma clínica médica, utilizando Programação Orientada a Objetos em Java.

O sistema busca representar os principais elementos envolvidos no atendimento clínico, como pacientes, profissionais, especialidades e consultas. Dessa forma, é possível estabelecer relacionamentos entre esses elementos e organizar de maneira estruturada o agendamento e o acompanhamento das consultas.

## 2. Objetivo do sistema

O objetivo do sistema é permitir o gerenciamento de pacientes, profissionais e especialidades, possibilitando também o agendamento, o cancelamento e a conclusão de consultas, além da consulta de informações da agenda da clínica.

O projeto tem como finalidade aplicar conceitos de Programação Orientada a Objetos, principalmente:

* criação e utilização de classes e objetos;
* encapsulamento de atributos;
* utilização de construtores;
* relacionamentos entre objetos;
* organização das responsabilidades entre as classes;
* implementação de regras de negócio;
* utilização de recursos de depuração;
* utilização de recursividade de forma coerente com o problema.

## 3. Descrição das funcionalidades

O sistema possui as seguintes funcionalidades principais:

* Cadastro de pacientes;
* Cadastro de profissionais, cada um vinculado a uma especialidade;
* Cadastro de especialidades;
* Listagem de pacientes, profissionais e especialidades cadastrados;
* Agendamento de consultas, com escolha do paciente, da especialidade e do profissional;
* Cancelamento de consultas (de forma geral, por paciente ou por profissional);
* Conclusão de consultas (de forma geral, por paciente ou por profissional);
* Consulta dos horários ocupados de um profissional;
* Consulta das consultas de um paciente;
* Utilização de dados de teste para facilitar a execução e a validação do sistema.

As operações são disponibilizadas por meio de um menu executado no terminal.

## 4. Descrição das classes

### 4.1 ClinicaMedica

A classe `ClinicaMedica` é responsável pelo gerenciamento dos principais objetos do sistema e pela coordenação das operações que envolvem mais de uma classe, como o agendamento de uma consulta.

Ela mantém arrays de pacientes, profissionais, especialidades e consultas, cada um acompanhado de um contador com a quantidade de posições realmente preenchidas:

* `pacientes: Paciente[]` e `totalPacientes: int`;
* `profissionais: Profissional[]` e `totalProfissionais: int`;
* `especialidades: Especialidade[]` e `totalEspecialidades: int`;
* `consultas: Consulta[]` e `totalConsultas: int`.

Seus métodos estão organizados da seguinte forma:

* **Consulta de dados:** `getNome()`, `getPacientes()`, `getProfissionais()`, `getEspecialidades()`, `getConsultas()`;
* **Cadastros:** `cadastrarPaciente()`, `cadastrarProfissional()`, `cadastrarEspecialidade()`;
* **Agendamento:** `agendarConsulta()`;
* **Seleção em lista numerada:** `escolherPaciente()`, `escolherEspecialidade()`, `escolherProfissional()`;
* **Cancelamento:** `cancelarConsulta()`, `cancelarConsultaPorPaciente()`, `cancelarConsultaPorProfissional()`;
* **Conclusão:** `concluirConsulta()`, `concluirConsultaPorPaciente()`, `concluirConsultaPorProfissional()`;
* **Consultas de informação:** `consultarHorariosOcupados()`, `consultarConsultasPorPaciente()`, `consultarPacientesCadastrados()`, `consultarProfissionaisCadastrados()`, `consultarEspecialidadesCadastradas()`;
* **Métodos privados:** `adicionarPaciente()`, `adicionarProfissional()`, `adicionarEspecialidade()`, `adicionarConsulta()` e `buscarPacientePorNome()`.

Os métodos `cadastrarX()` validam as regras de negócio antes de cadastrar, e delegam a inserção no array aos métodos privados `adicionarX()`.

### 4.2 Paciente

A classe `Paciente` representa um paciente cadastrado na clínica.

Seus principais atributos são:

* `nome`;
* `cpf`;
* `consultas`;
* `totalConsultas`.

A classe mantém o histórico de consultas do paciente e possui métodos para consultar seus dados, adicionar consultas e listar as consultas realizadas ou agendadas.

Principais métodos:

* `getNome()`;
* `getCpf()`;
* `getConsultas()`;
* `adicionarConsulta()`;
* `listarConsultas()`;
* `exibir()`.

### 4.3 Profissional

A classe `Profissional` representa um profissional de saúde da clínica.

Seus atributos são:

* `nome`;
* `registroProfissional`;
* `especialidade`;
* `consultas`;
* `totalConsultas`.

O array de consultas representa a agenda do profissional. É nesta classe que fica a verificação de conflito de horários, pois apenas o próprio profissional conhece a sua agenda.

Principais métodos:

* `getNome()`;
* `getRegistroProfissional()`;
* `getEspecialidade()`;
* `getConsultas()`;
* `getTotalConsultas()`;
* `adicionarConsulta()`;
* `possuiConsultaNoHorario()`;
* `listarHorariosOcupados()`;
* `horarioDisponivel()`;
* `exibir()`.

### 4.4 Especialidade

A classe `Especialidade` representa uma especialidade médica oferecida pela clínica (por exemplo, Cardiologia ou Pediatria) que só existirá caso tenha algum médico daquela especialidade lá. 

Possui os atributos:

* `nome`;
* `descricao`.

Seus principais métodos são:

* `getNome()`;
* `getDescricao()`;
* `exibir()`.

### 4.5 Consulta

A classe `Consulta` representa o atendimento marcado entre um paciente e um profissional em um determinado horário.

Ela possui:

* `paciente`;
* `profissional`;
* `dataHora`;
* `status`;
* `observacoes`.

Toda consulta é criada com o status `AGENDADA`. As regras de cancelamento e de conclusão ficam dentro da própria classe, que é a responsável por controlar o seu estado.

Principais métodos:

* `getPaciente()`;
* `getProfissional()`;
* `getDataHora()`;
* `getObservacoes()`;
* `getStatus()`;
* `cancelar()`;
* `concluir()`;
* `exibir()`.

### 4.6 StatusConsulta

`StatusConsulta` é uma enumeração (`enum`) que define os estados possíveis de uma consulta:

* `AGENDADA`;
* `REALIZADA`;
* `CANCELADA`.

O uso de um `enum` no lugar de um texto evita erros de digitação e garante que uma consulta só possa assumir um dos três estados.

### 4.7 Config

A classe `Config` centraliza as configurações do sistema, de modo que uma alteração de capacidade precise ser feita em um único lugar.

Possui os atributos estáticos:

* `qtdConsultas`;
* `qtdPacientes`;
* `qtdEspecialidades`;
* `qtdProfissionais`;
* `formatoDataHora`.

Os quatro primeiros definem o tamanho dos arrays usados no sistema. O atributo `formatoDataHora` define o formato de data e hora utilizado nas informações digitadas pelo usuário, e o método `lerDataHora()` ajusta para o formato escolhido a leitura desses dados.

Além das classes acima, a classe `Main` contém o menu executado no terminal e os dados de teste. Por ser apenas a interface de interação com o usuário, ela não é representada no diagrama de classes.

## 5. Relacionamentos entre as classes

O sistema utiliza diferentes tipos de relacionamentos entre suas classes. A classe `ClinicaMedica` mantém coleções de pacientes, profissionais, especialidades e consultas, caracterizando relacionamentos de agregação.

Um `Paciente` pode realizar várias `Consulta`s ao longo do tempo, e um `Profissional` pode atender várias consultas em horários diferentes. Cada `Consulta` conecta exatamente um paciente e um profissional, e cada `Profissional` possui exatamente uma `Especialidade`, embora uma mesma especialidade possa ser compartilhada por vários profissionais. A `Consulta` também se relaciona com o `StatusConsulta`, que indica o seu estado atual. Por fim, a classe `Config` é utilizada como dependência por `ClinicaMedica`, `Profissional` e `Consulta`.

As cardinalidades utilizadas são:

* `ClinicaMedica` 1 → 0..* `Paciente` — agregação;
* `ClinicaMedica` 1 → 0..* `Profissional` — agregação;
* `ClinicaMedica` 1 → 0..* `Especialidade` — agregação;
* `ClinicaMedica` 1 → 0..* `Consulta` — agregação;
* `Paciente` 1 — 0..* `Consulta` — associação;
* `Profissional` 1 — 0..* `Consulta` — associação;
* `Profissional` 0..* → 1 `Especialidade` — associação;
* `Consulta` 1 → 1 `StatusConsulta` — associação;
* `Config` → `ClinicaMedica`, `Profissional` e `Consulta` — dependência.

#### Diagrama de Classes (UML)

![Diagrama de Classes](UML_SistemaClinicaMedica.drawio.png)

## 6. Regras de negócio

Para um bom funcionamento clínica é necessário algumas regras de negócio.
Dentre elas:

* Não é permitido agendar uma consulta para um paciente inexistente;
* Um profissional não pode possuir duas consultas no mesmo horário;
* Uma consulta deve estar associada a um paciente e a um profissional;
* Um profissional deve possuir uma especialidade cadastrada;
* Não é permitido cancelar uma consulta que já tenha sido realizada;
* Ao concluir uma consulta, ela é marcada como realizada;
* Toda consulta é criada com o status `AGENDADA`;
* Não é permitido concluir uma consulta que já foi cancelada;
* O horário de uma consulta cancelada volta a ficar disponível para o profissional;
* A quantidade de cadastros e de consultas respeita os limites definidos na classe `Config`;

Essas regras são essenciais para um bom funcionamento do sistema. 

## 7. Dificuldades encontradas durante o desenvolvimento

Durante o desenvolvimento do projeto, uma das principais dificuldades foi organizar as responsabilidades entre as classes, evitando concentrar toda a lógica na `ClinicaMedica`. A solução adotada foi manter cada regra na classe que conhece os dados envolvidos (o conflito de horários em `Profissional`, o controle de estado em `Consulta`) e deixar para a `ClinicaMedica` apenas a coordenação das operações que envolvem mais de um objeto.

Outra dificuldade foi trabalhar sem o uso de `List`. Porém, com o uso da classe`Config` logo o problema foi resolvido.

Também foi necessário definir como localizar os objetos sem utilizar um atributo `id`. A solução foi realizar a busca pelo nome, permitindo digitar apenas parte dele, e exibir as opções encontradas em uma lista numerada. Dessa forma, o sistema consegue lidar com pessoas de nomes parecidos, e oferece ainda a opção de cadastrar um paciente no momento do agendamento caso ele ainda não exista.

Uma outra dificuldade foi em relação a limitação, pois algumas funcionalidades tiveram que ser adicionadas para o programa, a qualquer erro do usuario, não travar. A organização das diversas funções que tiveram que ser utilizadas também acabou atrapalhando, pois ao dar um erro, ficava dificil saber porquais motivos e o que tava dando errado. Além disso, a falta de uma equipe para fazer junto o trabalho contribuiu para a demora na entrega do sistema.

Também acabei não me atentando a usar recurvidade, pois ficaria somente para cumprir tabela.

## 8. Como executar

O projeto utiliza o Java 25 (o método `main` de instância e a classe `IO` fazem parte dessa versão). Com todos os arquivos `.java` dentro da pasta `src`, execute na raiz do projeto:

```bash
javac -d out src/*.java
java -cp out Main
```