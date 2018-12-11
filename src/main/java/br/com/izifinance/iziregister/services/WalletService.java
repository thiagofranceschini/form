package br.com.izifinance.iziregister.services;

import org.springframework.stereotype.Service;

@Service
public class WalletService {

	public Boolean receivingWalletDataTest(String address, String password) {
		//performar um post pra iziauth, assim que souber como
		System.out.println("***********teste do controller que chamará o serviço do iziauth");
		System.out.println(address);
		System.out.println(password);
		return true;
	}
}
