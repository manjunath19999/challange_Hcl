package com.db.awmd.challenge.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

	@Autowired
	AccountsRepository accountsRepository;

	private final Map<String, Account> accounts = new ConcurrentHashMap<>();

	@Override
	public void createAccount(Account account) throws DuplicateAccountIdException {
		Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
		if (previousAccount != null) {
			throw new DuplicateAccountIdException("Account id " + account.getAccountId() + " already exists!");
		}
	}

	@Override
	public Account getAccount(String accountId) {
		return accounts.get(accountId);
	}

	@Override
	public void clearAccounts() {
		accounts.clear();
	}

	@Override
	public Account save(Account account) {
		// TODO Auto-generated method stub
		return accountsRepository.save(account);
	}

}
