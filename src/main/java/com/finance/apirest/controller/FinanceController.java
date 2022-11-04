package com.finance.apirest.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


import com.finance.apirest.controller.Dtos.FinanceDto;
import com.finance.apirest.models.Movimentacao;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finance.apirest.models.Finance;
import com.finance.apirest.repository.FinanceRepository;

import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;


@RestController
@RequestMapping(value="/api")
@Api(tags = "Finance", description = "End-Point")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class FinanceController {

	@Autowired
	FinanceRepository financeRepository;

	// GET para listar todos os produtos.
	@ApiOperation(value="Retorna uma lista do Financeiro")
	@GetMapping("/finance")
	public ResponseEntity<List<Finance>> listaFinance(){
		List<Finance> finance = financeRepository.findAll();
		if (!finance.isEmpty()){
			return ResponseEntity.ok(finance);
		}
		return ResponseEntity.noContent().build();
	}

	// GET para listar finance por ID.
	@ApiOperation(value="Retorna uma movimentação financeira")
	@GetMapping("/finance/{id}")
	public ResponseEntity<Finance> listaFinanceUnico(@PathVariable(value="id") long id){
		Finance finance = financeRepository.findById(id);
		if (finance != null){
			return ResponseEntity.ok(finance);
		}
		return ResponseEntity.noContent().build();
	}

	// GET para finance por total RECEITA.
	@ApiOperation(value="Retorna o total de entradas")
	@GetMapping("/finance/receitas")
	public ResponseEntity<BigDecimal> listaFinanceReceita(){
		List<Finance> finance = financeRepository.findByStatus(Movimentacao.RECEITA);
		if (!finance.isEmpty()) {
			int countItem = 0;
			BigDecimal result = BigDecimal.valueOf(0);
			for (Finance value : finance) {
				result = result.add(value.getValor());
			}
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.noContent().build();
	}

	// GET para finance por total DESPESA
	@ApiOperation(value="Retorna o total de saidas")
	@GetMapping("/finance/despesas")
	public ResponseEntity<BigDecimal> listaFinanceDespesas(){
		List<Finance> finance = financeRepository.findByStatus(Movimentacao.DESPESA);
		if (!finance.isEmpty()) {
			int countItem = 0;
			BigDecimal result = BigDecimal.valueOf(0);
			for (Finance value : finance) {
				result = result.add(value.getValor());
			}
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.noContent().build();
	}

	// POST para gravar novo finance.
	@ApiOperation(value="Salva um finance")
	@PostMapping("/finance")
	public ResponseEntity<Finance> salvaFinance(@RequestBody @Valid FinanceDto financeDto) {
		Finance finance = new Finance();
		BeanUtils.copyProperties(financeDto, finance);
		finance.setDataMovimentacao(LocalDate.from(LocalDateTime.now(ZoneId.of("UTC"))));
		return ResponseEntity.status(HttpStatus.CREATED).body(financeRepository.save(finance));
		//return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(finance.getId()).toUri()).build();
	}

	// Delete - Excluir item por ID.
	@ApiOperation(value="Deleta um Finance")
	@DeleteMapping("/finance/{id}")
	public ResponseEntity<Void> deletaFinance(	Long id) {
		Optional<Finance> finance = financeRepository.findById(id);
		if (finance.isPresent()){
			financeRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	// PUT - Atualizar item por ID.
	@ApiOperation(value="Atualiza um Finance")
	@PutMapping("/finance/{id}")
	public ResponseEntity<String> atualizaFinance(Long id, @RequestBody @Valid FinanceDto financeDto) {
		Optional<Finance> atualizaFinance = financeRepository.findById(id);
		if (atualizaFinance.isPresent()){
			Finance finance = atualizaFinance.get();
			BeanUtils.copyProperties(financeDto, finance);
			financeRepository.save(finance);
			return ResponseEntity.ok().body("Atualizado com sucesso");
		}
		return ResponseEntity.notFound().build();
	}
}
