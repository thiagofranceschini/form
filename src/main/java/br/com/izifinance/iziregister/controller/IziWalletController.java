package br.com.izifinance.iziregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.izifinance.iziregister.dto.WalletInputDto;
import br.com.izifinance.iziregister.services.WalletService;

@RestController
@RequestMapping("/wallet")
public class IziWalletController {
	@Autowired
	private WalletService service;

	@PostMapping
	public ResponseEntity<?> validateWallet(@RequestBody WalletInputDto dto) {
		if (ObjectUtils.isEmpty(dto) || StringUtils.isEmpty(dto.getWalletAddress())
				|| StringUtils.isEmpty(dto.getPassword())) {
			return ResponseEntity.badRequest().body("Invalid Login and Password!");
		} else {
			Boolean login = service.receivingWalletDataTest(dto.getWalletAddress(), dto.getPassword());
			if (!login) {
				return ResponseEntity.badRequest().body("Login e senha incorretos");
			} else {
				return ResponseEntity.ok().body("Logado com sucesso!");
			}
		}
	}

}
