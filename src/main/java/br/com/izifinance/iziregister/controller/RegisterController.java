package br.com.izifinance.iziregister.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.izifinance.iziregister.dto.InputRegisterDto;
import br.com.izifinance.iziregister.dto.InputUpdateDto;
import br.com.izifinance.iziregister.dto.OutPutRegisterDto;
import br.com.izifinance.iziregister.email.Mailer;
import br.com.izifinance.iziregister.email.MessageConfirmation;
import br.com.izifinance.iziregister.model.Register;
import br.com.izifinance.iziregister.services.RegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService service;

	@Autowired
	private Mailer mailer;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findRegisterById(@PathVariable String id) {
		Register register = service.findById(id);
		return ResponseEntity.ok().body(new OutPutRegisterDto(register));
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findRegisterEmail(@PathVariable String email) {
		Register register = service.findByEmail(email);
		return ResponseEntity.ok().body(new OutPutRegisterDto(register));
	}

	@PostMapping()
	public ResponseEntity<?> saveRegister(@Valid @RequestBody InputRegisterDto registerDto) {
		Register savedRegister = service.saveNewRegister(registerDto.toRegister());
		mailer.send(new MessageConfirmation(savedRegister));
		return ResponseEntity.ok().body(new OutPutRegisterDto(savedRegister));
	}

	@PutMapping()
	public ResponseEntity<?> updateRegister(@Valid @RequestBody InputUpdateDto registerDto) {
		Register updatedRegister = service.updateRegister(registerDto.toRegister());
		return ResponseEntity.ok().body(new OutPutRegisterDto(updatedRegister));
	}
	
	@PostMapping("/profilepicture/id/{id}")
	public ResponseEntity<?>upload(@RequestParam(name="file")MultipartFile file, @PathVariable String id) throws IOException, URISyntaxException{
		URI uri = service.s3UploadAndSaveUriToRegister(file, id);
		return ResponseEntity.created(uri).build();
	}
}
