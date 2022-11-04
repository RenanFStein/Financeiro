package com.finance.apirest.repository;

import com.finance.apirest.models.Finance;
import com.finance.apirest.models.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FinanceRepository extends JpaRepository<Finance, Long>{
	Finance findById(long id);

    List<Finance> findByStatus(Movimentacao movimentacao);

}
