package br.com.izifinance.iziregister.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.izifinance.iziregister.model.Address;
import br.com.izifinance.iziregister.model.Register;

public class InputUpdateDto {

	@NotEmpty private String id;
	@NotEmpty private String cpf;
	@NotEmpty private String name;
	@NotNull private Date birthday;
	@NotEmpty private String email;
	@NotEmpty private String password;
	@NotEmpty private String phoneNumber;
	@NotEmpty private String street;
	@NotEmpty private String number;
	@NotEmpty private String complement;
	@NotEmpty private String neighborhood;
	@NotEmpty private String zipcode;
	@NotEmpty private String country;

	public InputUpdateDto() {
		super();
	}

	public InputUpdateDto(String id, String cpf, String name, Date birthday, String email, String password, String phoneNumber,
			String street, String number, String complement, String neighborhood, String zipcode, String country) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.zipcode = zipcode;
		this.country = country;
	}

	public Register toRegister() {
		Address address = new Address(this.street, this.number, this.complement, this.neighborhood, this.zipcode,
				this.country);
		Register register = new Register(this.cpf, this.name, this.birthday, this.email, this.password, this.phoneNumber, address);
		register.setId(this.id);
		return register;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
