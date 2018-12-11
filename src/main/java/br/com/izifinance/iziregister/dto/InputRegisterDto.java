package br.com.izifinance.iziregister.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.izifinance.iziregister.model.Address;
import br.com.izifinance.iziregister.model.Register;

public class InputRegisterDto {

	@NotEmpty private String name;
	@NotEmpty private String cpf;
	@NotNull private Date birthday;
	@NotEmpty private String email;
	@NotEmpty private String password;
	@NotEmpty private String phoneNumber;
	@NotEmpty private String addressStreet;
	@NotEmpty private String streetNumber;
	private String addressComplement;
	@NotEmpty private String neighborhood;
	@NotEmpty private String zipcode;
	@NotEmpty private String country;

	public InputRegisterDto() {
		super();
	}

	public Register toRegister() {
		Address address = new Address(this.addressStreet, this.streetNumber, this.addressComplement, this.neighborhood, this.zipcode,
				this.country);
		return new Register(this.cpf, this.name, this.birthday, this.email, this.password, this.phoneNumber, address);
	}

	public InputRegisterDto(String name, String cpf, String email, Date birthday, String password, String phoneNumber,
			String addressStreet, String streetNumber, String addressComplement, String neighborhood, String zipcode,
			String country) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.addressStreet = addressStreet;
		this.streetNumber = streetNumber;
		this.addressComplement = addressComplement;
		this.neighborhood = neighborhood;
		this.zipcode = zipcode;
		this.country = country;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
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
