package io.github.isguliyev.examples.bank;

import io.github.isguliyev.examples.bank.exceptions.InvalidOperationException;

import java.math.BigDecimal;
import java.math.MathContext;

public class BasicBankAccount {
    private final String accountNumber;
    private final BigDecimal annualInterestRate;
    private final MathContext mathContext;
    private BigDecimal balance;

    public BasicBankAccount(String accountNumber, BigDecimal annualInterestRate) {
        this.accountNumber = accountNumber;
        this.annualInterestRate = annualInterestRate;
        this.mathContext = MathContext.DECIMAL32;
        this.balance = BigDecimal.ZERO;
    }

    public void deposit(BigDecimal depositAmount) {
        if (depositAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOperationException("Deposit amount must be greater than 0");
        }

        this.balance = this.balance.add(depositAmount, this.mathContext);
    }

    public void withdraw(BigDecimal withdrawalAmount) {
        if (withdrawalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOperationException("Withdrawal amount must be greater than 0");
        }

        if (withdrawalAmount.compareTo(this.balance) > 0) {
            throw new InvalidOperationException("Withdrawal amount must be greater than balance");
        }

        this.balance = this.balance.subtract(withdrawalAmount, this.mathContext);
    }

    public BigDecimal calculateMonthlyFee() {
        final BigDecimal minimumMonthlyFee = BigDecimal.valueOf(10.0d);
        final BigDecimal monthlyFeePercentage = BigDecimal.valueOf(0.1d);

        return monthlyFeePercentage
            .multiply(this.balance, this.mathContext)
            .max(minimumMonthlyFee);
    }

    public BigDecimal calculateMonthlyInterest() {
        return this.balance
            .multiply(this.annualInterestRate, this.mathContext)
            .divide(BigDecimal.valueOf(1200.0d), this.mathContext);
    }

    public void applyMonthlyUpdate() {
        this.balance = this.balance
            .subtract(this.calculateMonthlyFee(), this.mathContext)
            .add(this.calculateMonthlyInterest(), this.mathContext);
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public BigDecimal getAnnualInterestRate() {
        return this.annualInterestRate;
    }

    public MathContext getMathContext() {
        return this.mathContext;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
}