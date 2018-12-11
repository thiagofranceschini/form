package br.com.izifinance.iziregister.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.izifinance.iziregister.model.Address;
import br.com.izifinance.iziregister.model.Register;

public class OutPutRegisterDto {
	private String id;
	private String cpf;
	private String name;
	private Date birthday;
	private String email;
	private String password;
	private String phoneNumber;
	private Date registerDate;
	private Address address;
	private List<String>imagesUrls = new ArrayList<>();
	
	public OutPutRegisterDto(Register register) {
		this.id = register.getId();
		this.cpf = register.getCpf();
		this.name = register.getName();
		this.birthday = register.getBirthday();
		this.email = register.getEmail();
		this.password = register.getPassword();
		this.phoneNumber = register.getPhoneNumber();
		this.registerDate = register.getRegisterDate();
		this.address = register.getAddress();
		this.imagesUrls = register.getUriImageProfile();
	}
	public OutPutRegisterDto() {
		super();
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<String> getImagesUrls() {
		return imagesUrls;
	}
	public void setImagesUrls(List<String> imagesUrls) {
		this.imagesUrls = imagesUrls;
	}
}
