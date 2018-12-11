package br.com.izifinance.iziregister.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.izifinance.iziregister.model.Register;
import br.com.izifinance.iziregister.model.StatusRegister;
import br.com.izifinance.iziregister.repositories.RegisterRepository;
import br.com.izifinance.iziregister.services.exception.CpfAlreadyInUseException;
import br.com.izifinance.iziregister.services.exception.EmailAlreadyInUseException;
import br.com.izifinance.iziregister.services.exception.ObjectNotFoundException;

@Service
public class RegisterService {

	@Autowired
	private RegisterRepository repository;
	@Autowired
	private S3Service s3service;

	public Register findById(String registerId) {
		return repository.findById(registerId)
				.orElseThrow(() -> new ObjectNotFoundException("registerId not Found: " + registerId));
	}
	
	public Register findByEmail(String email) {
		return repository.findByEmail(email)
				.orElseThrow(() -> new ObjectNotFoundException("e-mail not found: "+ email));
	}

	public Register saveRegister(Register register) {
		return repository.save(register);
	}

	public Register saveNewRegister(Register register) {
		
		//check if email already exists for another user on system
		Optional<Register> byEmail = repository.findByEmail(register.getEmail());
		if (byEmail.isPresent()) {
			throw new EmailAlreadyInUseException("Email is Already in use!" + register.getEmail());
		}

		//check if cpf already exists for another user on system
		Optional<Register> byCpf = repository.findByCpf(register.getCpf());
		if (byCpf.isPresent()) {
			throw new CpfAlreadyInUseException("CPF is already in use! " + register.getCpf());
		}

		register.setStatus(StatusRegister.WAITING);
		register.setRegisterDate(Date.from(Instant.now()));
		return repository.save(register);
	}

	public Register updateRegister(Register newRegister) {
		Register registerOnDb = findById(newRegister.getId());
	
		//check if email already exists for another user on system
		Optional<Register> byEmail = repository.findByEmail(newRegister.getEmail());
		if (byEmail.isPresent()) {
			if (!byEmail.get().equals(newRegister)) {
				throw new EmailAlreadyInUseException("Email is Already in use!" + newRegister.getEmail());
			}
		}

		//check if cpf already exists for another user on system
		Optional<Register> byCpf = repository.findByCpf(newRegister.getCpf());
		if (byCpf.isPresent()) {
			if(!byCpf.get().equals(newRegister)) {
				throw new CpfAlreadyInUseException("CPF is already in use! " + newRegister.getCpf());
			}
		}
		
		

		Register updated = update(newRegister, registerOnDb);
		return repository.save(updated);
	}

	private Register update(Register newRegister, Register registerOnDb) {
		registerOnDb.setName(newRegister.getName());
		registerOnDb.setEmail(newRegister.getEmail());
		registerOnDb.setPassword(newRegister.getPassword());
		registerOnDb.setPhoneNumber(newRegister.getPhoneNumber());
		registerOnDb.setAddress(newRegister.getAddress());
		return registerOnDb;
	}
	
	public void setUriImageProfile(Register register, URI uri) {
		register.addUrlImageProfile(uri.toString());
	}
	

	public URI s3UploadAndSaveUriToRegister(MultipartFile file, String id) throws IOException, URISyntaxException {
		Register register = findById(id);
		int size = register.getUriImageProfile().size();
		String imageId = id+String.valueOf(size);
		URI uri = s3service.uploadFile(file, imageId);
		setUriImageProfile(register, uri);
		saveRegister(register);
		return uri;
	}
}
