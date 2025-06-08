# Sistema de Gestão de Seguros
> Este é um projeto de um aplicativo de desktop para gerenciamento de apólices de seguro de veículos. 
Desenvolvido em Java com a biblioteca Swing para a interface gráfica, o sistema abrange o cadastro de segurados 
(pessoas físicas e jurídicas), veículos, apólices e sinistros, implementando diversas regras de negócio do setor de seguros.

## Funcionalidades Principais
> - Cadastro de Segurados: Gerenciamento completo (CRUD) de segurados, diferenciando entre Pessoa Física e Pessoa Jurídica (Empresa).
> - Gestão de Veículos: Cadastro e manutenção de informações dos veículos segurados.
> - Emissão de Apólices:
> > - Inclusão de apólices com validações complexas e cálculo automático de prêmio e franquia.
> > - Geração de número de apólice único baseado no ano, documento do segurado e placa do veículo.
> > - Sistema de bônus que concede descontos ao segurado com base no histórico de sinistros.
> - Registro de Sinistros:
> > - Inclusão de sinistros, validando a existência de uma apólice vigente para o veículo na data do ocorrido.
> > - Geração de número de sinistro sequencial, vinculado à apólice.
> > - Tratamento de erros de validação de forma agrupada através de exceções customizadas.
> - Persistência de Dados: O sistema utiliza uma camada de abstração para persistência de objetos em arquivos (CadastroObjetos), com um padrão de Data Access Object (DAO) genérico para facilitar a manutenção.

## Estrutura do Projeto
> O código-fonte está organizado em pacotes, seguindo uma arquitetura em camadas para separar as responsabilidades:
> - ```br.edu.cs.poo.ac.seguro.entidades```: Contém as classes de modelo (POJOs) que representam as entidades do sistema, como ```Apolice```, ```Segurado```, ```Veiculo``` e ```Sinistro```.
> - ```br.edu.cs.poo.ac.seguro.daos```: Responsável pelo acesso e persistência dos dados. Utiliza um ```DAOGenerico``` que abstrai as operações de CRUD, e especializações como ```ApoliceDAO```, ```VeiculoDAO```, etc.
> - ```br.edu.cs.poo.ac.seguro.mediators```: Orquestra as operações e contém toda a lógica de negócio do sistema. Valida os dados recebidos da interface do usuário antes de interagir com os DAOs. Exemplos incluem ```ApoliceMediator``` e ```SinistroMediator```.
> - ```br.edu.cs.poo.ac.seguro.telas```: Contém as classes da interface gráfica do usuário (GUI), desenvolvidas com a biblioteca Swing. Exemplos são ```TelaSeguradoPessoa```, ```TelaInclusaoApolice``` e ```TelaInclusaoSinistro```.
> - ```br.edu.cs.poo.ac.seguro.excecoes```: Define exceções personalizadas, como ```ExcecaoValidacaoDados```, para um tratamento de erros mais robusto.
> - ```br.edu.cs.poo.ac.seguro.testes```: Contém testes unitários e de integração utilizando JUnit para garantir a qualidade e o correto funcionamento das regras de negócio e da persistência de dados.

## Regras de Negócio Implementadas
> O projeto implementa regras de negócio específicas, cuja lógica pode ser observada principalmente nas classes "Mediator".
> ### Criação de Apólice (```ApoliceMediator```)
> > - Número da Apólice: O número é gerado concatenando o ano atual, o CPF/CNPJ do segurado e a placa do veículo.
> > - Cálculo do Prêmio: O valor do prêmio é calculado com base em 3% do valor máximo segurado. Há um acréscimo de 20% para veículos de locadoras. O bônus acumulado pelo segurado é subtraído para gerar o valor final.
> > - Cálculo da Franquia: Corresponde a 130% do valor do prêmio antes da aplicação do bônus.
> > - Bonificação: Ao final da inclusão de uma apólice, o sistema verifica se o segurado teve algum sinistro no ano anterior. Se não houver, um bônus de 30% sobre o valor do prêmio é creditado ao segurado.
> ### Inclusão de Sinistro (```SinistroMediator```)
> > - Validação: Um sinistro só pode ser registrado se houver uma apólice vigente (com menos de um ano de emissão) para o veículo na data da ocorrência.
> > - Limite de Valor: O valor do sinistro não pode exceder o valor máximo segurado definido na apólice.
> > - Número do Sinistro: É gerado com o prefixo "S", seguido pelo número da apólice e um sequencial de 3 dígitos, que é incrementado a cada novo sinistro para a mesma apólice.

## Como Executar
> Cada classe no pacote ```br.edu.cs.poo.ac.seguro.telas``` possui um método ```main```, permitindo que cada tela seja executada de forma independente.
> Para executar o cadastro de um segurado pessoa física:
> - `br.edu.cs.poo.ac.seguro.telas.TelaSeguradoPessoa`
> Para executar a inclusão de uma apólice:
> - `br.edu.cs.poo.ac.seguro.telas.TelaInclusaoApolice`

## Dependências
- Java SE: Linguagem de programação principal.
- Swing: Para a construção da interface gráfica.
- JUnit 5: Para a execução dos testes automatizados.
- Lombok: Utilizado para reduzir código boilerplate nas classes de entidade (via anotações como `@Getter`, `@Setter`).
- CadastroObjetos: Biblioteca externa para lidar com a persistência de objetos em arquivos.
