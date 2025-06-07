package br.edu.cs.poo.ac.seguro.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.excecoes.ExcecaoValidacaoDados;
import br.edu.cs.poo.ac.seguro.mediators.DadosSinistro;
import br.edu.cs.poo.ac.seguro.mediators.SinistroMediator;

public class TelaInclusaoSinistro extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Mediator
	private SinistroMediator mediator = SinistroMediator.getInstancia();

	// Componentes
	private JPanel contentPane;
	private JTextField txtPlaca;
	private JFormattedTextField txtDataHoraSinistro;
	private JTextField txtUsuarioRegistro;
	private JTextField txtValorSinistro;
	private JComboBox<String> comboTipoSinistro;
	private JButton btnIncluir;
	private JButton btnLimpar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInclusaoSinistro frame = new TelaInclusaoSinistro();
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
	public TelaInclusaoSinistro() {
		setTitle("Inclusão de Sinistro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa do Veículo:");
		lblPlaca.setBounds(25, 30, 150, 14);
		contentPane.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(225, 27, 120, 20);
		contentPane.add(txtPlaca);
		txtPlaca.setColumns(10);
		
		JLabel lblDataHora = new JLabel("Data e Hora do Sinistro:");
		lblDataHora.setBounds(25, 60, 180, 14);
		contentPane.add(lblDataHora);
		
		try {
			txtDataHoraSinistro = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##"));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			txtDataHoraSinistro = new JFormattedTextField();
		}
		txtDataHoraSinistro.setToolTipText("Use o formato dd/MM/yyyy HH:mm");
		txtDataHoraSinistro.setBounds(225, 57, 150, 20);
		contentPane.add(txtDataHoraSinistro);
		
		JLabel lblUsuario = new JLabel("Usuário do Registro:");
		lblUsuario.setBounds(25, 90, 150, 14);
		contentPane.add(lblUsuario);
		
		txtUsuarioRegistro = new JTextField();
		txtUsuarioRegistro.setColumns(10);
		txtUsuarioRegistro.setBounds(225, 87, 210, 20);
		contentPane.add(txtUsuarioRegistro);
		
		JLabel lblTipoSinistro = new JLabel("Tipo do Sinistro:");
		lblTipoSinistro.setBounds(25, 120, 150, 14);
		contentPane.add(lblTipoSinistro);
		
		comboTipoSinistro = new JComboBox<String>();
		comboTipoSinistro.setBounds(225, 117, 210, 22);
		contentPane.add(comboTipoSinistro);
		
		JLabel lblValorSinistro = new JLabel("Valor do Sinistro (R$):");
		lblValorSinistro.setBounds(25, 150, 180, 14);
		contentPane.add(lblValorSinistro);
		
		txtValorSinistro = new JTextField();
		txtValorSinistro.setColumns(10);
		txtValorSinistro.setBounds(225, 147, 120, 20);
		contentPane.add(txtValorSinistro);
		
		JPanel panelAcoes = new JPanel();
		panelAcoes.setBounds(10, 220, 444, 70);
		contentPane.add(panelAcoes);
		panelAcoes.setLayout(null);
		
		btnIncluir = new JButton("Incluir Sinistro");
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
		
		preencherTiposSinistro();
	}

	private void preencherTiposSinistro() {
		List<TipoSinistro> tipos = new ArrayList<>(Arrays.asList(TipoSinistro.values()));
		tipos.sort(Comparator.comparing(TipoSinistro::getNome));
		
		for (TipoSinistro tipo : tipos) {
			comboTipoSinistro.addItem(tipo.getNome());
		}
	}
	
	private void limparCampos() {
		txtPlaca.setText("");
		txtDataHoraSinistro.setValue(null);
		txtUsuarioRegistro.setText("");
		txtValorSinistro.setText("");
		if (comboTipoSinistro.getItemCount() > 0) {
			comboTipoSinistro.setSelectedIndex(0);
		}
	}
	
	private void acaoIncluir() {
		String placa = txtPlaca.getText();
		String usuario = txtUsuarioRegistro.getText();
		LocalDateTime dataHora;
		double valor;
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			dataHora = LocalDateTime.parse(txtDataHoraSinistro.getText(), formatter);
		} catch (DateTimeParseException ex) {
			JOptionPane.showMessageDialog(this, "Data e hora do sinistro inválida. Use o formato dd/MM/yyyy HH:mm.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			valor = Double.parseDouble(txtValorSinistro.getText().replace(',', '.'));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Valor do sinistro deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String nomeTipoSelecionado = (String) comboTipoSinistro.getSelectedItem();
		int codigoTipo = -1;
		for (TipoSinistro tipo : TipoSinistro.values()) {
			if (tipo.getNome().equals(nomeTipoSelecionado)) {
				codigoTipo = tipo.getCodigo();
				break;
			}
		}
		
		if (codigoTipo == -1) {
			JOptionPane.showMessageDialog(this, "Erro ao obter o tipo de sinistro.", "Erro Interno", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		DadosSinistro dados = new DadosSinistro(placa, dataHora, usuario, valor, codigoTipo);
		
		try {
			String numeroGerado = mediator.incluirSinistro(dados, LocalDateTime.now());
			JOptionPane.showMessageDialog(this, "Sinistro incluído com sucesso! Anote o número do sinistro: " + numeroGerado, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			limparCampos();
			
		} catch (ExcecaoValidacaoDados e) {
			String erros = String.join("\n", e.getMensagens());
			JOptionPane.showMessageDialog(this, "Erros de validação:\n" + erros, "Erro ao Incluir Sinistro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
