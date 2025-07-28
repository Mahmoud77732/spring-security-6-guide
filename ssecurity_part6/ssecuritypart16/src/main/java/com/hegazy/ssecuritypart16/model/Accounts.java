package com.hegazy.ssecuritypart16.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Accounts {

	@Column(name = "customer_id")
	private long customerId;

	@Id
	@Column(name="account_number", columnDefinition = "BIGINT")
	private long accountNumber;

	@Column(name="account_type")
	private String accountType;

	@Column(name = "branch_address")
	private String branchAddress;

	@Column(name = "create_dt")
	private Date createDt;

    public Accounts() {
    }

    public Accounts(long customerId, long accountNumber, String accountType, String branchAddress, Date createDt) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
        this.createDt = createDt;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "Accounts [customerId=" + customerId + ", accountNumber=" + accountNumber + ", accountType="
                + accountType + ", branchAddress=" + branchAddress + ", createDt=" + createDt + "]";
    }

}
