package br.edu.cs.poo.ac.seguro.telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.mediators.ApoliceMediator;
import br.edu.cs.poo.ac.seguro.mediators.DadosVeiculo;
import br.edu.cs.poo.ac.seguro.mediators.RetornoInclusaoApolice;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TelaInclusaoApolice extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Mediator
	private ApoliceMediator mediator = ApoliceMediator.getInstancia();

	// Componentes
	private JPanel contentPane;
	private JTextField txtCpfCnpj;
	private JTextField txtPlaca;
	private JTextField txtAno;
	private JTextField txtValorMaximo;
	private JComboBox<String> comboCategoria;
	private JButton btnIncluir;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInclusaoApolice frame = new TelaInclusaoApolice();
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
	public TelaInclusaoApolice() {
		setTitle("Inclusão de Apólice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpfCnpj = new JLabel("CPF ou CNPJ do Segurado:");
		lblCpfCnpj.setBounds(25, 30, 170, 14);
		contentPane.add(lblCpfCnpj);
		
		txtCpfCnpj = new JTextField();
		txtCpfCnpj.setBounds(195, 27, 240, 20);
		contentPane.add(txtCpfCnpj);
		txtCpfCnpj.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa do Veículo:");
		lblPlaca.setBounds(25, 60, 120, 14);
		contentPane.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setColumns(10);
		txtPlaca.setBounds(195, 57, 120, 20);
		contentPane.add(txtPlaca);
		
		JLabel lblAno = new JLabel("Ano do Veículo:");
		lblAno.setBounds(25, 90, 120, 14);
		contentPane.add(lblAno);
		
		txtAno = new JTextField();
		txtAno.setColumns(10);
		txtAno.setBounds(195, 87, 86, 20);
		contentPane.add(txtAno);
		
		JLabel lblCategoria = new JLabel("Categoria do Veículo:");
		lblCategoria.setBounds(25, 120, 150, 14);
		contentPane.add(lblCategoria);
		
		comboCategoria = new JComboBox<String>();
		comboCategoria.setBounds(195, 117, 240, 22);
		contentPane.add(comboCategoria);
		
		JLabel lblValorMaximo = new JLabel("Valor Máximo Segurado (R$):");
		lblValorMaximo.setBounds(25, 150, 180, 14);
		contentPane.add(lblValorMaximo);
		
		txtValorMaximo = new JTextField();
		txtValorMaximo.setColumns(10);
		txtValorMaximo.setBounds(195, 147, 120, 20);
		contentPane.add(txtValorMaximo);
		
		JPanel panelAcoes = new JPanel();
		panelAcoes.setBounds(10, 220, 444, 70);
		contentPane.add(panelAcoes);
		panelAcoes.setLayout(null);
		
		btnIncluir = new JButton("Incluir Apólice");
		btnIncluir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoIncluir();
			}
		});
		btnIncluir.setBounds(70, 20, 140, 30);
		panelAcoes.add(btnIncluir);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(240, 20, 120, 30);
		panelAcoes.add(btnLimpar);
		
		preencherCategorias();
	}

	private void preencherCategorias() {
		List<CategoriaVeiculo> categorias = new ArrayList<>(Arrays.asList(CategoriaVeiculo.values()));
		
		categorias.sort(Comparator.comparing(CategoriaVeiculo::getNome));
		
		for (CategoriaVeiculo categoria : categorias) {
			comboCategoria.addItem(categoria.getNome());
		}
	}
	
	private void limparCampos() {
		txtCpfCnpj.setText("");
		txtPlaca.setText("");
		txtAno.setText("");
		txtValorMaximo.setText("");
		if (comboCategoria.getItemCount() > 0) {
			comboCategoria.setSelectedIndex(0);
		}
	}
	
	private String removerFormatacao(String texto) {
		if (texto == null) {
			return "";
		}
		return texto.replaceAll("[^a-zA-Z0-9]", "");
	}
	
	private void acaoIncluir() {
		String cpfCnpj = removerFormatacao(txtCpfCnpj.getText());
		String placa = txtPlaca.getText();
		int ano;
		BigDecimal valorMaximo;
		

		try {
			ano = Integer.parseInt(txtAno.getText());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Ano do veículo deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			valorMaximo = new BigDecimal(txtValorMaximo.getText().replace(',', '.'));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Valor máximo segurado deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String nomeCategoriaSelecionada = (String) comboCategoria.getSelectedItem();
		int codigoCategoria = -1;
		for (CategoriaVeiculo cat : CategoriaVeiculo.values()) {
			if (cat.getNome().equals(nomeCategoriaSelecionada)) {
				codigoCategoria = cat.getCodigo();
				break;
			}
		}
		
		if (codigoCategoria == -1) {
			JOptionPane.showMessageDialog(this, "Erro ao obter a categoria selecionada.", "Erro Interno", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		DadosVeiculo dados = new DadosVeiculo(cpfCnpj, placa, ano, valorMaximo, codigoCategoria);
		
		RetornoInclusaoApolice retorno = mediator.incluirApolice(dados);
		
		if (retorno.getMensagemErro() != null) {
			JOptionPane.showMessageDialog(this, retorno.getMensagemErro(), "Erro ao Incluir Apólice", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Apólice incluída com sucesso! Anote o número da apólice: " + retorno.getNumeroApolice(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			limparCampos();
		}
	}
}
