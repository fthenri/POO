package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoEmpresa extends Segurado {

	private static final long serialVersionUID = 1L;
	
	private String cnpj;
	private double faturamento;
	private boolean ehLocadoraDeVeiculos;
	
	@Override
    public String getIdUnico() {
        return getCnpj();
    }
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public double getFaturamento() {
		return faturamento;
	}
	
	public void setFaturamento(double faturamento) {
		this.faturamento = faturamento;
	}
	
	public boolean getEhLocadoraDeVeiculos() {
		return ehLocadoraDeVeiculos;
	}
	
	public void setEhLocadoraDeVeiculos(boolean ehLocadoraDeVeiculos) {
		this.ehLocadoraDeVeiculos = ehLocadoraDeVeiculos;
	}
	
	public SeguradoEmpresa(String nome, Endereco endereco, LocalDate dataAbertura, BigDecimal bonus,
			String cnpj, double faturamento, boolean ehLocadoraDeVeiculos) {
		super(nome, endereco, dataAbertura, bonus);
		this.cnpj = cnpj;
		this.faturamento = faturamento;
		this.ehLocadoraDeVeiculos = ehLocadoraDeVeiculos;
	}
	
	@Override
	public boolean isEmpresa() {
		return true;
	}
	
	public LocalDate getDataAbertura() {
		return super.getDataCriacao();
	}
	
	public void setDataAbertura(LocalDate dataAbertura) {
		super.setDataCriacao(dataAbertura);
	}
}
