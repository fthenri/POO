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
