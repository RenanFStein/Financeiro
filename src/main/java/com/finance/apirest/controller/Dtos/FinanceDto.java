package com.finance.apirest.controller.Dtos;

import com.finance.apirest.models.Finance;
import com.finance.apirest.models.Movimentacao;
import com.finance.apirest.repository.FinanceRepository;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;


public class FinanceDto {

        @NotBlank(message = "Campo não informado.")
        @Pattern(regexp = "^[A-Z]+(.)*", message = "A primeira letra deve ser Maiuscula")
        private String descricao;


        @NotNull(message = "Campo não informado.")
        @Min(value = 0, message = "Valor deve ser maior que 0.")
        private BigDecimal valor;

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Campos não informado. Informe 'RECEITA' ou 'DESPESA'.")
        private Movimentacao status;

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public BigDecimal getValor() {
                return valor;
        }

        public void setValor(BigDecimal valor) {
                this.valor = valor;
        }

        public Movimentacao getStatus() {
                return status;
        }

        public void setStatus(Movimentacao status) {
                this.status = status;
        }
}
