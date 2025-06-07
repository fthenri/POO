package br.edu.cs.poo.ac.seguro.mediators;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.Registro;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;
import br.edu.cs.poo.ac.seguro.excecoes.ExcecaoValidacaoDados;

public class SinistroMediator {
	
	private VeiculoDAO daoVeiculo = new VeiculoDAO();
	private ApoliceDAO daoApolice = new ApoliceDAO();
	private SinistroDAO daoSinistro = new SinistroDAO();
	private static SinistroMediator instancia;

	public static SinistroMediator getInstancia() {
		if (instancia == null)
			instancia = new SinistroMediator();
		return instancia;
	}

	private SinistroMediator() {}

	/* - esta classe deve ser um Singleton.
	 * - o método recebe como um dos parâmetros o tipo DadosSinistro, que deve ser criado no
	 * pacote br.edu.cs.poo.ac.seguro.mediators, e ter os seguintes atributos, com set e 
	 * get e construtor que inicializa os atributos, todos públicos:
	 * private String placa;
	 * private LocalDateTime dataHoraSinistro;
	 * private String usuarioRegistro;
	 * private double valorSinistro;
	 * private int codigoTipoSinistro;
	 * - as mensagens de erros de validação devem ser acumuladas em uma lista, que é atributo da classe
	 * ExcecaoValidacaoDados. Este atributo deve se chamar mensagens, ter um get público, e 
	 * ser inicializado no construtor da classe. Esta classe deve ser criada no pacote 
	 * br.edu.cs.poo.ac.seguro.excecoes.
	 * - a exceção será lançada se o sinistro não for gerado e incluído no DAO.
	 * - dados não pode ser null
	 * - data/hora do sinistro não pode ser null
	 * - data/hora do sinistro deve ser menor que a data/hora atual 
	 * - placa do veículo não pode ser null nem branco 
	 * - placa, se informada, deve ser de um veículo cadastrado
	 * - usuário do registro não pode ser null nem branco
	 * - valor do sinistro deve ser maior que zero   
	 * - o código do tipo de sinistro deve ser um correspondente aos tipos de sinistro 
	 * especificados no enum TipoSinistro
	 * - um sinistro só deve ser registrado se houver apólice vigente em relação à data e hora do sinistro 
	 * (a apólice tem vigência de 1 ano, a contar da sua data de início de vigência), que cubra o veículo em questão
	 * - para saber isso, deve-se buscar todas as apólices, e procurar a que esteja vigente e que 
	 * tenha veículo associado cuja placa é igual à placa informada nos dados do sinistro.
	 * - uma vez encontrada a apólice, deve-se validar o valor do sinistro, que não pode ser maior 
	 * do que o valor máximo segurado constante na apólice encontrada.  
	 * - após estas validações, deve-se formar o número do sinistro, que é: 
	 * "S" + numeroDaApoliceDeCobertura + sequencial 
	 * - o sequencial deve formar o número do sinistro sempre com 3 dígitos, completando-se com zeros à
	 * esquerda números que são menores do que 100.
	 * - o sequencial deve ser inferido da seguinte forma: 
	 * - se não existir gravado sinistro com mesmo número de apólice do sinsitro a ser incluído, sequencial = 1
	 * - se existir gravado sinistro com mesmo número de apólice do sinsitro a ser incluído, sequencial = 
	 * maior sequencial entre os sinistros encontrados mais um.
	 * - uma forma de encontrar o maior sequencial dentre os sinistros é ordená-los por sequencial. para isso,
	 * deve-se usar o esquema de ordenação do JAVA (Collections.sort) e um Comparator específico, 
	 * que deve ficar no pacote dos mediators, e se chamar ComparadorSinistroSequencial. 
	 * - por fim, deve-se instanciar o sinistro com os dados recebidos, e o veículo lido, setar nele o número da
	 * apólice e o sequencial.  
	 */
	public String incluirSinistro(DadosSinistro dados, LocalDateTime dataHoraAtual) throws ExcecaoValidacaoDados {
		List<String> erros = new ArrayList<>();

		if (dados == null) {
			erros.add("Dados do sinistro devem ser informados");
			throw new ExcecaoValidacaoDados(erros);
		}

		if (dados.getDataHoraSinistro() == null) {
			erros.add("Data/hora do sinistro deve ser informada");
		} else if (dados.getDataHoraSinistro().isAfter(dataHoraAtual)) {
			erros.add("Data/hora do sinistro deve ser menor que a data/hora atual");
		}

		if (StringUtils.ehNuloOuBranco(dados.getPlaca())) {
			erros.add("Placa do Veiculo deve ser informada");
		}

		if (StringUtils.ehNuloOuBranco(dados.getUsuarioRegistro())) {
			erros.add("Usuario do registro de sinistro deve ser informado");
		}

		if (dados.getValorSinistro() <= 0) {
			erros.add("Valor do sinistro deve ser maior que zero");
		}
		
		TipoSinistro tipoSinistro = TipoSinistro.getTipoSinistro(dados.getCodigoTipoSinistro());
		if (tipoSinistro == null) {
			erros.add("Codigo do tipo de sinistro invalido");
		}

		Veiculo veiculo = null;
		if (!StringUtils.ehNuloOuBranco(dados.getPlaca())) {
			veiculo = daoVeiculo.buscar(dados.getPlaca());
			if (veiculo == null) {
				erros.add("Veiculo não cadastrado");
			}
		}

		if (!erros.isEmpty()) {
			throw new ExcecaoValidacaoDados(erros);
		}

		Apolice apoliceVigente = null;
		for (Registro reg : daoApolice.buscarTodos()) {
			Apolice ap = (Apolice) reg;
			if (ap.getVeiculo() != null && ap.getVeiculo().getPlaca().equalsIgnoreCase(dados.getPlaca())) {
				LocalDateTime inicioVigencia = ap.getDataInicioVigencia().atStartOfDay();
				LocalDateTime fimVigencia = inicioVigencia.plusYears(1);
				if (!dados.getDataHoraSinistro().isBefore(inicioVigencia) && dados.getDataHoraSinistro().isBefore(fimVigencia)) {
					apoliceVigente = ap;
					break;
				}
			}
		}

		if (apoliceVigente == null) {
			erros.add("Nao existe apolice vigente para o veiculo");
			throw new ExcecaoValidacaoDados(erros);
		}
		
		BigDecimal valorSinistroBD = new BigDecimal(dados.getValorSinistro());
		if (valorSinistroBD.compareTo(apoliceVigente.getValorMaximoSegurado()) > 0) {
			erros.add("Valor do sinistro nao pode ultrapassar o valor maximo segurado constante na apolice");
			throw new ExcecaoValidacaoDados(erros);
		}

		int sequencial = 1;
		int maxSequencial = 0;
		for (Sinistro sin : daoSinistro.buscarTodos()) {
			if (sin.getNumeroApolice() != null && sin.getNumeroApolice().equals(apoliceVigente.getNumero())) {
				if (sin.getSequencial() > maxSequencial) {
					maxSequencial = sin.getSequencial();
				}
			}
		}
		sequencial = maxSequencial + 1;

		String numeroApolice = apoliceVigente.getNumero();
		String numeroSinistro = "S" + numeroApolice + String.format("%03d", sequencial);

		Sinistro novoSinistro = new Sinistro(
			numeroSinistro,
			veiculo,
			dados.getDataHoraSinistro(),
			dataHoraAtual,
			dados.getUsuarioRegistro(),
			valorSinistroBD,
			tipoSinistro
		);
		novoSinistro.setNumeroApolice(numeroApolice);
		novoSinistro.setSequencial(sequencial);
		
		daoSinistro.incluir(novoSinistro);
		
		return numeroSinistro;
	}
}