package br.edu.cs.poo.ac.seguro.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoEmpresaMediator;

public class TelaSeguradoEmpresa extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private SeguradoEmpresaMediator mediator = SeguradoEmpresaMediator.getInstancia();

	private JPanel contentPane;
	private JFormattedTextField txtCnpj;
	private JTextField txtNome;
	private JFormattedTextField txtDataAbertura;
	private JTextField txtFaturamento;
	private JTextField txtBonus;
	private JCheckBox chkEhLocadora;
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
	private JPanel panelDadosEmpresa;
	private JPanel panelEndereco;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSeguradoEmpresa frame = new TelaSeguradoEmpresa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaSeguradoEmpresa() {
		setTitle("Cadastro de Segurado - Pessoa Jurídica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(22, 26, 46, 14);
		contentPane.add(lblCnpj);
		
		try {
			txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		} catch (ParseException e) {
			e.printStackTrace();
			txtCnpj = new JFormattedTextField();
		}
		txtCnpj.setBounds(72, 23, 150, 20);
		contentPane.add(txtCnpj);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoBuscar();
			}
		});
		btnBuscar.setBounds(232, 22, 89, 23);
		contentPane.add(btnBuscar);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoNovo();
			}
		});
		btnNovo.setBounds(331, 22, 89, 23);
		contentPane.add(btnNovo);
		
		
		panelDadosEmpresa = new JPanel();
		panelDadosEmpresa.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dados da Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDadosEmpresa.setBounds(10, 68, 614, 130);
		contentPane.add(panelDadosEmpresa);
		panelDadosEmpresa.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome (Razão Social):");
		lblNome.setBounds(10, 30, 150, 14);
		panelDadosEmpresa.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(140, 27, 460, 20);
		panelDadosEmpresa.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblDataAbertura = new JLabel("Data Abertura:");
		lblDataAbertura.setBounds(10, 60, 100, 14);
		panelDadosEmpresa.add(lblDataAbertura);
		
		try {
			txtDataAbertura = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
			txtDataAbertura = new JFormattedTextField();
		}
		txtDataAbertura.setBounds(105, 57, 100, 20);
		panelDadosEmpresa.add(txtDataAbertura);
		
		JLabel lblFaturamento = new JLabel("Faturamento:");
		lblFaturamento.setBounds(10, 90, 80, 14);
		panelDadosEmpresa.add(lblFaturamento);
		
		txtFaturamento = new JTextField();
		txtFaturamento.setBounds(95, 87, 133, 20);
		panelDadosEmpresa.add(txtFaturamento);
		txtFaturamento.setColumns(10);
		
		JLabel lblBonus = new JLabel("Bônus:");
		lblBonus.setBounds(250, 90, 46, 14);
		panelDadosEmpresa.add(lblBonus);
		
		txtBonus = new JTextField();
		txtBonus.setBounds(295, 87, 133, 20);
		panelDadosEmpresa.add(txtBonus);
		txtBonus.setColumns(10);
		
		chkEhLocadora = new JCheckBox("É locadora de veículos?");
		chkEhLocadora.setBounds(440, 86, 170, 23);
		panelDadosEmpresa.add(chkEhLocadora);
		

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

		configurarEstadoInicial();
	}

	private String removerFormatacao(String texto) {
		if (texto == null) {
			return "";
		}
		return texto.replaceAll("[^0-9]", "");
	}

	private void configurarEstadoInicial() {
		txtCnpj.setEnabled(true);
		btnNovo.setEnabled(true);
		btnBuscar.setEnabled(true);
		
		txtNome.setEnabled(false);
		txtDataAbertura.setEnabled(false);
		txtFaturamento.setEnabled(false);
		txtBonus.setEnabled(false);
		chkEhLocadora.setEnabled(false);
		txtLogradouro.setEnabled(false);
		txtNumero.setEnabled(false);
		txtComplemento.setEnabled(false);
		txtCep.setEnabled(false);
		txtCidade.setEnabled(false);
		txtEstado.setEnabled(false);
		txtPais.setEnabled(false);
		
		btnIncluirAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnCancelar.setEnabled(false);
		
		btnIncluirAlterar.setText("Incluir");
	}
	
	private void limparCampos() {
		if (txtCnpj.isEnabled()) {
			txtCnpj.setValue(null);
		}
		txtNome.setText("");
		txtDataAbertura.setValue(null);
		txtFaturamento.setText("");
		txtBonus.setText("");
		chkEhLocadora.setSelected(false);
		txtLogradouro.setText("");
		txtNumero.setText("");
		txtComplemento.setText("");
		txtCep.setValue(null);
		txtCidade.setText("");
		txtEstado.setText("");
		txtPais.setText("");
	}
	
	private void configurarModoEdicao() {
		txtCnpj.setEnabled(false);
		btnNovo.setEnabled(false);
		btnBuscar.setEnabled(false);

		txtNome.setEnabled(true);
		txtDataAbertura.setEnabled(true);
		txtFaturamento.setEnabled(true);
		txtBonus.setEnabled(true);
		chkEhLocadora.setEnabled(true);
		txtLogradouro.setEnabled(true);
		txtNumero.setEnabled(true);
		txtComplemento.setEnabled(true);
		txtCep.setEnabled(true);
		txtCidade.setEnabled(true);
		txtEstado.setEnabled(true);
		txtPais.setEnabled(true);

		btnIncluirAlterar.setEnabled(true);
		btnCancelar.setEnabled(true);
	}

	private void acaoNovo() {
		String cnpj = removerFormatacao(txtCnpj.getText());
		if (cnpj.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Para criar um novo segurado, preencha o CNPJ.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		SeguradoEmpresa segurado = mediator.buscarSeguradoEmpresa(cnpj);
		if (segurado != null) {
			JOptionPane.showMessageDialog(this, "CNPJ já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			limparCampos();
			configurarModoEdicao();
			btnIncluirAlterar.setText("Incluir");
			btnExcluir.setEnabled(false);
		}
	}

	private void acaoBuscar() {
		String cnpj = removerFormatacao(txtCnpj.getText());
		if (cnpj.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha o CNPJ para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		SeguradoEmpresa segurado = mediator.buscarSeguradoEmpresa(cnpj);
		if (segurado == null) {
			JOptionPane.showMessageDialog(this, "Segurado não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			txtNome.setText(segurado.getNome());
			txtDataAbertura.setText(segurado.getDataAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			txtFaturamento.setText(String.valueOf(segurado.getFaturamento()));
			txtBonus.setText(segurado.getBonus().toPlainString());
			chkEhLocadora.setSelected(segurado.getEhLocadoraDeVeiculos());
			
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
			
			configurarModoEdicao();
			btnIncluirAlterar.setText("Alterar");
			btnExcluir.setEnabled(true);
		}
	}

	private void acaoIncluirAlterar() {
		String cnpj = removerFormatacao(txtCnpj.getText());
		String nome = txtNome.getText();
		boolean ehLocadora = chkEhLocadora.isSelected();
		
		LocalDate dataAbertura = null;
		double faturamento = 0.0;
		BigDecimal bonus = BigDecimal.ZERO;

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataAbertura = LocalDate.parse(txtDataAbertura.getText(), formatter);
		} catch (DateTimeParseException ex) {
			JOptionPane.showMessageDialog(this, "Data de abertura inválida. Use o formato dd/MM/aaaa.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			faturamento = Double.parseDouble(txtFaturamento.getText());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Faturamento deve ser um valor numérico.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
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

		Endereco endereco = new Endereco(
			txtLogradouro.getText(),
			removerFormatacao(txtCep.getText()),
			txtNumero.getText(),
			txtComplemento.getText(),
			txtPais.getText(),
			txtEstado.getText(),
			txtCidade.getText()
		);

		SeguradoEmpresa segurado = new SeguradoEmpresa(nome, endereco, dataAbertura, bonus, cnpj, faturamento, ehLocadora);

		String msgErro = null;
		String msgSucesso = null;
		
		if (btnIncluirAlterar.getText().equals("Incluir")) {
			msgErro = mediator.incluirSeguradoEmpresa(segurado);
			msgSucesso = "Segurado incluído com sucesso!";
		} else {
			msgErro = mediator.alterarSeguradoEmpresa(segurado);
			msgSucesso = "Segurado alterado com sucesso!";
		}

		if (msgErro != null) {
			JOptionPane.showMessageDialog(this, msgErro, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, msgSucesso, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			configurarEstadoInicial();
			limparCampos();
		}
	}

	private void acaoExcluir() {
		int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este segurado?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION) {
			String cnpj = removerFormatacao(txtCnpj.getText());
			String msgErro = mediator.excluirSeguradoEmpresa(cnpj);
			
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
