package com.db.awmd.challenge.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.repository.AccountsRepository;

import lombok.Getter;

@Service
public class AccountsService {
	private final Logger log = LoggerFactory.getLogger(AccountsService.class);

	BigDecimal bd = new BigDecimal(0);

	@Getter
	private final AccountsRepository accountsRepository;

	@Autowired
	EmailNotificationService emailNotificationService;

	@Autowired
	public AccountsService(AccountsRepository accountsRepository) {
		this.accountsRepository = accountsRepository;
	}

	public void createAccount(Account account) {
		this.accountsRepository.createAccount(account);
	}

	public Account getAccount(String accountId) {
		return this.accountsRepository.getAccount(accountId);
	}

	public synchronized String amountTransfer(String fromAcc, String toAcc, BigDecimal amt) {

		String returnMessage = "success";
		try {
			if (amt.compareTo(BigDecimal.ZERO) > 0) {

				Account fromAccount = accountsRepository.getAccount(fromAcc);

				if (amt.compareTo(fromAccount.getBalance()) == 1) {
					log.info("insufficient balance!");
					returnMessage = "insufficient balance!";
				} else {
					Account toAccount = accountsRepository.getAccount(toAcc);
					BigDecimal addedAmt = toAccount.getBalance().add(amt);
					toAccount.setBalance(addedAmt);
					// accountsRepository.save(toAccount);
					emailNotificationService.notifyAboutTransfer(toAccount, "amount transfered successfully!");
				}
			} else {
				log.info("Please enter a valid amount");
				returnMessage = "Please enter a valid amount";
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return returnMessage;
	}

}
