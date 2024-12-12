package org.back.ebankingbackend.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.ebankingbackend.entities.BankAccount;
import org.back.ebankingbackend.entities.CurrentAccount;
import org.back.ebankingbackend.entities.Customer;
import org.back.ebankingbackend.entities.SavingAccount;
import org.back.ebankingbackend.exceptions.CustomerNotFoundException;
import org.back.ebankingbackend.repositories.AccountOperationRepository;
import org.back.ebankingbackend.repositories.BankAccountRepository;
import org.back.ebankingbackend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j

public class BankAccountServiceImpl implements BankAccountService{
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving new Customer");
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public BankAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public BankAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public BankAccount saveBankAccount(double initialBalance, String type, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not founc");
        BankAccount bankAccount;
        if (type.equals("current")){
            bankAccount = new CurrentAccount();
        }else {
            bankAccount = new SavingAccount();
        }
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setCustomer(customer);
        return null;
    }

    @Override
    public List<Customer> listCustomer() {
        return null;
    }

    @Override
    public BankAccount getBankAccount(String accountId) {
        return null;
    }

    @Override
    public void debit(String accountId, double amount, String description) {

    }

    @Override
    public void credit(String accountId, double amount, String description) {

    }

    @Override
    public void transfer(String accoundIdSource, String accountIdDestination, double amount) {

    }
}
