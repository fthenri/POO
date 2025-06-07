package br.edu.cs.poo.ac.seguro.telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoPessoaMediator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class TelaSeguradoPessoa extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Mediator
	private SeguradoPessoaMediator mediator = SeguradoPessoaMediator.getInstancia();

	// Componentes da Tela
	private JPanel contentPane;
	private JFormattedTextField txtCpf;
	private JTextField txtNome;
	private JFormattedTextField txtDataNascimento;
	private JTextField txtRenda;
	private JTextField txtBonus;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JFormattedTextField txtCep;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtPais;
	
	private JButton btnNovo;
	private JButton btnBuscar;
	private JButton btnIncluirAlterar;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JButton btnLimpar;
	private JPanel panelDadosPessoais;
	private JPanel panelEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSeguradoPessoa frame = new TelaSeguradoPessoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaSeguradoPessoa() {
		setTitle("Cadastro de Segurado - Pessoa Física");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// PAINEL DE BUSCA
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(22, 26, 46, 14);
		contentPane.add(lblCpf);
		
		try {
			txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			e.printStackTrace();
			txtCpf = new JFormattedTextField();
		}
		txtCpf.setBounds(62, 23, 130, 20);
		contentPane.add(txtCpf);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoBuscar();
			}
		});
		btnBuscar.setBounds(202, 22, 89, 23);
		contentPane.add(btnBuscar);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoNovo();
			}
		});
		btnNovo.setBounds(301, 22, 89, 23);
		contentPane.add(btnNovo);
		
		
		// PAINEL DE DADOS PESSOAIS
		panelDadosPessoais = new JPanel();
		panelDadosPessoais.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dados Pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDadosPessoais.setBounds(10, 68, 614, 130);
		contentPane.add(panelDadosPessoais);
		panelDadosPessoais.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 30, 46, 14);
		panelDadosPessoais.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(52, 27, 540, 20);
		panelDadosPessoais.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblDataNascimento = new JLabel("Data Nasc.:");
		lblDataNascimento.setBounds(10, 60, 80, 14);
		panelDadosPessoais.add(lblDataNascimento);
		
		try {
			txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
			txtDataNascimento = new JFormattedTextField();
		}
		txtDataNascimento.setBounds(85, 57, 100, 20);
		panelDadosPessoais.add(txtDataNascimento);
		
		JLabel lblRenda = new JLabel("Renda:");
		lblRenda.setBounds(10, 90, 46, 14);
		panelDadosPessoais.add(lblRenda);
		
		txtRenda = new JTextField();
		txtRenda.setBounds(52, 87, 133, 20);
		panelDadosPessoais.add(txtRenda);
		txtRenda.setColumns(10);
		
		JLabel lblBonus = new JLabel("Bônus:");
		lblBonus.setBounds(250, 90, 46, 14);
		panelDadosPessoais.add(lblBonus);
		
		txtBonus = new JTextField();
		txtBonus.setBounds(295, 87, 133, 20);
		panelDadosPessoais.add(txtBonus);
		txtBonus.setColumns(10);
		

		// PAINEL DE ENDEREÇO
		panelEndereco = new JPanel();
		panelEndereco.setLayout(null);
		panelEndereco.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEndereco.setBounds(10, 209, 614, 160);
		contentPane.add(panelEndereco);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(10, 30, 80, 14);
		panelEndereco.add(lblLogradouro);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(85, 27, 343, 20);
		panelEndereco.add(txtLogradouro);
		
		JLabel lblNumero = new JLabel("Número:");
		lblNumero.setBounds(438, 30, 56, 14);
		panelEndereco.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(492, 27, 112, 20);
		panelEndereco.add(txtNumero);
		
		JLabel lblComplemento = new JLabel("Compl.:");
		lblComplemento.setBounds(10, 60, 65, 14);
		panelEndereco.add(lblComplemento);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(85, 57, 343, 20);
		panelEndereco.add(txtComplemento);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(10, 90, 46, 14);
		panelEndereco.add(lblCep);
		
		try {
			txtCep = new JFormattedTextField(new MaskFormatter("#####-###"));
		} catch (ParseException e) {
			e.printStackTrace();
			txtCep = new JFormattedTextField();
		}
		txtCep.setBounds(85, 87, 100, 20);
		panelEndereco.add(txtCep);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 120, 46, 14);
		panelEndereco.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(85, 117, 215, 20);
		panelEndereco.add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(310, 120, 46, 14);
		panelEndereco.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(355, 117, 50, 20);
		panelEndereco.add(txtEstado);
		
		JLabel lblPais = new JLabel("País:");
		lblPais.setBounds(438, 120, 46, 14);
		panelEndereco.add(lblPais);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(472, 117, 132, 20);
		panelEndereco.add(txtPais);
		

		// PAINEL DE AÇÕES
		JPanel panelAcoes = new JPanel();
		panelAcoes.setBounds(10, 396, 614, 74);
		contentPane.add(panelAcoes);
		panelAcoes.setLayout(null);
		
		btnIncluirAlterar = new JButton("Incluir");
		btnIncluirAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoIncluirAlterar();
			}
		});
		btnIncluirAlterar.setBounds(162, 21, 120, 23);
		panelAcoes.add(btnIncluirAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoExcluir();
			}
		});
		btnExcluir.setBounds(292, 21, 89, 23);
		panelAcoes.add(btnExcluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configurarEstadoInicial();
				limparCampos();
			}
		});
		btnCancelar.setBounds(391, 21, 99, 23);
		panelAcoes.add(btnCancelar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(500, 21, 89, 23);
		panelAcoes.add(btnLimpar);

		// Configura o estado inicial da tela
		configurarEstadoInicial();
	}
	
	/**
	 * Remove caracteres não numéricos de uma string (para CPF, CEP).
	 */
	private String removerFormatacao(String texto) {
		if (texto == null) {
			return "";
		}
		return texto.replaceAll("[^0-9]", "");
	}
	
	/**
	 * Configura a tela para o estado inicial (apenas busca permitida).
	 */
	private void configurarEstadoInicial() {
		// Habilita campos de chave e botões de busca/novo
		txtCpf.setEnabled(true);
		btnNovo.setEnabled(true);
		btnBuscar.setEnabled(true);
		
		// Desabilita campos de dados
		txtNome.setEnabled(false);
		txtDataNascimento.setEnabled(false);
		txtRenda.setEnabled(false);
		txtBonus.setEnabled(false);
		txtLogradouro.setEnabled(false);
		txtNumero.setEnabled(false);
		txtComplemento.setEnabled(false);
		txtCep.setEnabled(false);
		txtCidade.setEnabled(false);
		txtEstado.setEnabled(false);
		txtPais.setEnabled(false);
		
		// Desabilita botões de ação
		btnIncluirAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnCancelar.setEnabled(false);
		
		// Configura texto do botão principal
		btnIncluirAlterar.setText("Incluir");
	}
	
	/**
	 * Limpa todos os campos de texto.
	 */
	private void limparCampos() {
		if (txtCpf.isEnabled()) {
			txtCpf.setValue(null);
		}
		txtNome.setText("");
		txtDataNascimento.setValue(null);
		txtRenda.setText("");
		txtBonus.setText("");
		txtLogradouro.setText("");
		txtNumero.setText("");
		txtComplemento.setText("");
		txtCep.setValue(null);
		txtCidade.setText("");
		txtEstado.setText("");
		txtPais.setText("");
	}
	
	/**
	 * Alterna a tela para o modo de edição (novo ou alteração).
	 */
	private void configurarModoEdicao() {
		// Desabilita busca
		txtCpf.setEnabled(false);
		btnNovo.setEnabled(false);
		btnBuscar.setEnabled(false);
		
		// Habilita campos de dados
		txtNome.setEnabled(true);
		txtDataNascimento.setEnabled(true);
		txtRenda.setEnabled(true);
		txtBonus.setEnabled(true);
		txtLogradouro.setEnabled(true);
		txtNumero.setEnabled(true);
		txtComplemento.setEnabled(true);
		txtCep.setEnabled(true);
		txtCidade.setEnabled(true);
		txtEstado.setEnabled(true);
		txtPais.setEnabled(true);

		// Habilita botões de ação
		btnIncluirAlterar.setEnabled(true);
		btnCancelar.setEnabled(true);
	}
	
	/**
	 * Lógica do botão "Novo".
	 */
	private void acaoNovo() {
		String cpf = removerFormatacao(txtCpf.getText());
		if (cpf.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Para criar um novo segurado, preencha o CPF.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		SeguradoPessoa segurado = mediator.buscarSeguradoPessoa(cpf);
		if (segurado != null) {
			JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			limparCampos(); // Limpa para garantir que não há dados de busca anterior
			configurarModoEdicao();
			btnIncluirAlterar.setText("Incluir");
			btnExcluir.setEnabled(false);
		}
	}
	
	/**
	 * Lógica do botão "Buscar".
	 */
	private void acaoBuscar() {
		String cpf = removerFormatacao(txtCpf.getText());
		if (cpf.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha o CPF para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		SeguradoPessoa segurado = mediator.buscarSeguradoPessoa(cpf);
		if (segurado == null) {
			JOptionPane.showMessageDialog(this, "Segurado não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			// Preenche os campos com os dados encontrados
			txtNome.setText(segurado.getNome());
			txtDataNascimento.setText(segurado.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			txtRenda.setText(String.valueOf(segurado.getRenda()));
			txtBonus.setText(segurado.getBonus().toPlainString());
			
			Endereco end = segurado.getEndereco();
			if (end != null) {
				txtLogradouro.setText(end.getLogradouro());
				txtNumero.setText(end.getNumero());
				txtComplemento.setText(end.getComplemento());
				txtCep.setText(end.getCep());
				txtCidade.setText(end.getCidade());
				txtEstado.setText(end.getEstado());
				txtPais.setText(end.getPais());
			}
			
			// Muda para o modo de edição/consulta
			configurarModoEdicao();
			btnIncluirAlterar.setText("Alterar");
			btnExcluir.setEnabled(true);
		}
	}
	
	/**
	 * Lógica do botão "Incluir" / "Alterar".
	 */
	private void acaoIncluirAlterar() {
		// 1. Coletar dados da tela
		String cpf = removerFormatacao(txtCpf.getText());
		String nome = txtNome.getText();
		
		LocalDate dataNascimento = null;
		double renda = 0.0;
		BigDecimal bonus = BigDecimal.ZERO;
		
		// 2. Validar e converter os dados
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataNascimento = LocalDate.parse(txtDataNascimento.getText(), formatter);
		} catch (DateTimeParseException ex) {
			JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato dd/MM/aaaa.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			renda = Double.parseDouble(txtRenda.getText());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Renda deve ser um valor numérico.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			if(txtBonus.getText() != null && !txtBonus.getText().trim().isEmpty()){
				bonus = new BigDecimal(txtBonus.getText());
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Bônus deve ser um valor numérico.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Coleta dados de endereço
		Endereco endereco = new Endereco(
			txtLogradouro.getText(),
			removerFormatacao(txtCep.getText()),
			txtNumero.getText(),
			txtComplemento.getText(),
			txtPais.getText(),
			txtEstado.getText(),
			txtCidade.getText()
		);
		
		// 3. Montar o objeto SeguradoPessoa
		SeguradoPessoa segurado = new SeguradoPessoa(nome, endereco, dataNascimento, bonus, cpf, renda);
		
		// 4. Chamar o mediator
		String msgErro = null;
		String msgSucesso = null;
		
		if (btnIncluirAlterar.getText().equals("Incluir")) {
			msgErro = mediator.incluirSeguradoPessoa(segurado);
			msgSucesso = "Segurado incluído com sucesso!";
		} else {
			msgErro = mediator.alterarSeguradoPessoa(segurado);
			msgSucesso = "Segurado alterado com sucesso!";
		}
		
		// 5. Exibir resultado e resetar a tela
		if (msgErro != null) {
			JOptionPane.showMessageDialog(this, msgErro, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, msgSucesso, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			configurarEstadoInicial();
			limparCampos();
		}
	}
	
	/**
	 * Lógica do botão "Excluir".
	 */
	private void acaoExcluir() {
		int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este segurado?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION) {
			String cpf = removerFormatacao(txtCpf.getText());
			String msgErro = mediator.excluirSeguradoPessoa(cpf);
			
			if (msgErro != null) {
				JOptionPane.showMessageDialog(this, msgErro, "Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Segurado excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				configurarEstadoInicial();
				limparCampos();
			}
		}
	}
}
