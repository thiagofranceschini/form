package br.com.izifinance.iziregister.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.izifinance.iziregister.model.Register;

public interface RegisterRepository extends MongoRepository<Register, String> {

	Optional<Register> findByCpf(String cpf);
	
	Optional<Register> findByEmail(String email);
}
