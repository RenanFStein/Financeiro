package com.finance.apirest.models;



import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="TB_FINANCE")
public class Finance implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Campo não informado.")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "A primeira letra deve ser Maiuscula")
	private String descricao;


	@NotNull(message = "Campo não informado.")
	@Min(value = 0, message = "Valor deve ser maior que 0.")
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campos não informado. Informe 'RECEITA' ou 'DESPESA'.")
	private Movimentacao status;

	private LocalDate dataMovimentacao;

	public Movimentacao getStatus() {
		return status;
	}

	public void setStatus(Movimentacao status) {
		this.status = status;
	}

	public LocalDate getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDate dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


}
