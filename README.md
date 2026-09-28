# Sistema de Clínica Médica

## 1. Descrição do problema

O projeto consiste no desenvolvimento de um sistema para gerenciamento de uma clínica médica, utilizando Programação Orientada a Objetos em Java.

O sistema busca representar os principais elementos envolvidos no atendimento clínico, como pacientes, profissionais, especialidades e consultas. Dessa forma, é possível estabelecer relacionamentos entre esses elementos e organizar as informações de maneira estruturada.

## 2. Objetivo do sistema

O objetivo do sistema é permitir o gerenciamento de pacientes, profissionais e especialidades, possibilitando também o agendamento, cancelamento e conclusão de consultas.

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
* Cadastro de profissionais;
* Cadastro de especialidades;
* Listagem de pacientes;
* Listagem de profissionais;
* Listagem de especialidades;
* Agendamento de consultas;
* Cancelamento de consultas;
* Conclusão de consultas;
* Consulta dos horários ocupados de um profissional;
* Consulta das consultas de um paciente;
* Utilização de dados de teste para facilitar a execução e validação do sistema.

As operações são disponibilizadas por meio de um menu executado no terminal.

## 4. Descrição das classes

### 4.1 ClinicaMedica

A classe `ClinicaMedica` é responsável pelo gerenciamento dos principais objetos do sistema e pela coordenação das operações que envolvem mais de uma classe, como o agendamento de uma consulta.

Ela mantém arrays de pacientes, profissionais, especialidades e consultas:

* `pacientes: Paciente[]`;
* `profissionais: Profissional[]`;
* `especialidades: Especialidade[]`;
* `consultas: Consulta[]`.

Entre seus principais métodos estão:

* `cadastrarPaciente()`;
* `cadastrarProfissional()`;
* `cadastrarEspecialidade()`;
* `agendarConsulta()`;
* `escolherPaciente()`;
* `escolherEspecialidade()`;
* `escolherProfissional()`;
* `cancelarConsulta()`;
* `concluirConsulta()`;
* `consultarHorariosOcupados()`;
* `consultarConsultasPorPaciente()`.

### 4.2 Paciente

A classe `Paciente` representa um paciente cadastrado na clínica.

Seus principais atributos são:

* `nome`;
* `cpf`;
* `consultas`;
* `totalConsultas`.

A classe mantém as consultas do paciente e possui métodos para consultar seus dados, adicionar consultas e listar suas consultas.

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

O array de consultas representa a agenda do profissional, sendo também responsável por verificar se existe outra consulta no mesmo horário.

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

A classe `Especialidade` representa uma especialidade médica oferecida pela clínica.

Possui os atributos:

* `nome`;
* `descricao`.

Seus principais métodos são:

* `getNome()`;
* `getDescricao()`;
* `exibir()`.

### 4.5 Consulta

A classe `Consulta` representa o atendimento marcado entre um paciente e um profissional em determinado horário.

Ela possui:

* `paciente`;
* `profissional`;
* `dataHora`;
* `status`;
* `observacoes`.

Toda consulta é criada com o status `AGENDADA`. A própria classe é responsável pelas operações de cancelamento e conclusão da consulta.

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

`StatusConsulta` é uma enumeração (`enum`) que representa os estados possíveis de uma consulta:

* `AGENDADA`;
* `REALIZADA`;
* `CANCELADA`.

### 4.7 Config

A classe `Config` centraliza as configurações utilizadas pelo sistema.

Possui os atributos:

* `qtdConsultas`;
* `qtdPacientes`;
* `qtdEspecialidades`;
* `qtdProfissionais`;
* `formatoDataHora`.

Os quatro primeiros definem os limites dos arrays utilizados no sistema.

O atributo `formatoDataHora` define o formato utilizado para exibição das datas e horários.

Seu principal método é:

* `lerDataHora()`.

Além das classes acima, a classe `Main` contém o menu executado no terminal e os dados de teste. Por ser responsável apenas pela interação com o usuário, ela não é representada no diagrama de classes.

## 5. Relacionamentos entre as classes

O sistema utiliza diferentes tipos de relacionamentos entre suas classes.

A classe `ClinicaMedica` mantém arrays de pacientes, profissionais, especialidades e consultas, caracterizando relacionamentos de agregação.

Um `Paciente` pode possuir várias `Consulta`s, enquanto um `Profissional` pode possuir várias consultas em sua agenda.

Cada `Consulta` está associada a um paciente e a um profissional.

Cada `Profissional` possui uma `Especialidade`, e uma mesma especialidade pode ser associada a vários profissionais.

A `Consulta` também possui um `StatusConsulta`, que representa o estado atual da consulta.

A classe `Config` é utilizada como dependência pelas classes que precisam das configurações do sistema.

As cardinalidades utilizadas são:

* `ClinicaMedica` 1 → 0..* `Paciente` — agregação;
* `ClinicaMedica` 1 → 0..* `Profissional` — agregação;
* `ClinicaMedica` 1 → 0..* `Especialidade` — agregação;
* `ClinicaMedica` 1 → 0..* `Consulta` — agregação;
* `Paciente` 1 → 0..* `Consulta` — associação;
* `Profissional` 1 → 0..* `Consulta` — associação;
* `Profissional` 0..* → 1 `Especialidade` — associação;
* `Consulta` 1 → 1 `Paciente` — associação;
* `Consulta` 1 → 1 `Profissional` — associação;
* `Consulta` 1 → 1 `StatusConsulta` — associação;
* `Config` → `ClinicaMedica`, `Profissional` e `Consulta` — dependência.

#### Diagrama de Classes (UML)

![Diagrama de Classes](UML_SistemaClinicaMedica.drawio.png)

## 6. Regras de negócio

O sistema possui regras para representar o funcionamento básico da clínica.

Entre as principais regras estão:

* Não é permitido agendar uma consulta para um paciente inexistente;
* um profissional não pode possuir duas consultas no mesmo horário;
* uma consulta deve estar associada a um paciente e a um profissional;
* um profissional deve possuir uma especialidade cadastrada;
* não é permitido cancelar uma consulta que já tenha sido realizada;
* ao concluir uma consulta, ela é marcada como realizada;
* toda consulta é criada com o status `AGENDADA`;
* não é permitido concluir uma consulta que já foi cancelada;
* o horário de uma consulta cancelada volta a ficar disponível;
* a quantidade de cadastros e consultas respeita os limites definidos na classe `Config`.

Essas regras permitem que as informações do sistema sejam mantidas de forma coerente.

## 7. Dificuldades encontradas durante o desenvolvimento

Durante o desenvolvimento do projeto, uma das principais dificuldades foi organizar as responsabilidades entre as classes e definir quais informações deveriam pertencer a cada objeto.

Também foi necessário estabelecer corretamente os relacionamentos entre `Paciente`, `Profissional`, `Especialidade` e `Consulta`, evitando concentrar toda a lógica em uma única classe.

Outra dificuldade foi trabalhar sem utilizar `List`. O uso de arrays exigiu o controle da quantidade de posições utilizadas, sendo necessário utilizar contadores e os limites definidos pela classe `Config`.

Durante os testes, foram utilizados recursos de depuração (`debug`) para acompanhar a execução do programa, identificar erros e verificar o comportamento dos objetos.

Também foi necessário criar validações para evitar que entradas inválidas do usuário causassem erros ou encerrassem o programa.

A utilização da recursividade também exigiu atenção para que ela fosse aplicada de maneira adequada ao problema proposto, evitando uma implementação artificial apenas para atender ao requisito do projeto.

## 8. Como executar

O projeto utiliza o **Java 25**.

Com todos os arquivos `.java` dentro da pasta `src`, execute na raiz do projeto:

```bash
javac -d out src/*.java
java -cp out Main
```
