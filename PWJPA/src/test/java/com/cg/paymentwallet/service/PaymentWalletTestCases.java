package com.cg.paymentwallet.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.paymentwallet.dto.Customer;
import com.cg.paymentwallet.dto.Wallet;
import com.cg.paymentwallet.exception.PaymentWalletException;

public class PaymentWalletTestCases {
	private static IPaymentsWalletService paymentwalletservice = null;

	@BeforeClass
	public static void setUpBeforeClass() throws PaymentWalletException {
		paymentwalletservice = new PaymentWalletService();

	}

	@Test(expected=PaymentWalletException.class)
	public void customerPassRegistrationtest() throws PaymentWalletException {
		Customer wallet = new Customer();
		Wallet wall=wallet.getWallet();
		wallet.setPhoneNumber("9703806992");
		wallet.setName("saikiran");
		wallet.setEmailId("balmur@saikiran@gmail.com");
		wallet.setAge(21);
		wallet.setGender("male");
		wall.setBalance(new BigDecimal(2000));
		wallet.setWallet(wall);
		Customer customer = paymentwalletservice.registerCustomer(wallet);
		assertNotNull(customer);
	}

	@Test(expected=PaymentWalletException.class)
	public void customerFailRegistrationtestWithPhoneNumber() throws PaymentWalletException {
		Customer wallet = new Customer();
		Wallet wall=wallet.getWallet();
		wallet.setPhoneNumber("9703");
		wallet.setName("saikiran");
		wallet.setEmailId("balmur@saikiran@gmail.com");
		wallet.setAge(21);
		wallet.setGender("male");
		wall.setBalance(new BigDecimal(2000));
		wallet.setWallet(wall);
		Customer customer = paymentwalletservice.registerCustomer(wallet);
		assertNull(customer);
	}

	@Test(expected=PaymentWalletException.class)
	public void customerSecondFailRegistrationtestwithEmailId() throws PaymentWalletException {
		Customer wallet = new Customer();
		Wallet wall=wallet.getWallet();
		wallet.setPhoneNumber("9703806992");
		wallet.setName("saikiran");
		wallet.setEmailId("balmur");
		wallet.setAge(21);
		wallet.setGender("male");
		wall.setBalance(new BigDecimal(2000));
		wallet.setWallet(wall);
		Customer customer = paymentwalletservice.registerCustomer(wallet);
		assertNull(customer);
	}

	@Test(expected=PaymentWalletException.class)
	public void customerSecondFailRegistrationtestwithName() throws PaymentWalletException {
		Customer wallet = new Customer();
		Wallet wall=wallet.getWallet();
		wallet.setPhoneNumber("9703806992");
		wallet.setName(" ");
		wallet.setEmailId("balmur@saikiran@gmail.com");
		wallet.setAge(21);
		wallet.setGender("male");
		wall.setBalance(new BigDecimal(2000));
		wallet.setWallet(wall);
		Customer customer = paymentwalletservice.registerCustomer(wallet);
		assertNull(customer);
	}

	@Test(expected=PaymentWalletException.class)
	public void customerSecondFailRegistrationtestwithAge() throws PaymentWalletException {
		Customer wallet = new Customer();
		Wallet wall=wallet.getWallet();
		wallet.setPhoneNumber("9703806992");
		wallet.setName("saikiran");
		wallet.setEmailId("balmur@saikiran@gmail.com");
		wallet.setAge(200);
		wallet.setGender("male");
		wall.setBalance(new BigDecimal(2000));
		wallet.setWallet(wall);
		Customer customer = paymentwalletservice.registerCustomer(wallet);
		assertNull(customer);
	}

	@Test(expected=PaymentWalletException.class)
	public void customerSecondFailRegistrationtestwithGender() throws PaymentWalletException {
		Customer wallet = new Customer();
		Wallet wall=wallet.getWallet();
		wallet.setPhoneNumber("9703806992");
		wallet.setName("saikiran");
		wallet.setEmailId("balmur@saikiran@gmail.com");
		wallet.setAge(21);
		wallet.setGender("hai");
		wall.setBalance(new BigDecimal(2000));
		wallet.setWallet(wall);
		Customer customer = paymentwalletservice.registerCustomer(wallet);
		assertNull(customer);
	}

	@Test(expected=PaymentWalletException.class)
	public void depositMoneyFailWithPhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.depositMoney("9703", new BigDecimal(2000));
		assertNull(customer);
	}

	@Test(expected=PaymentWalletException.class)
	public void depositMoneyPassWithPhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.depositMoney("9703806992", new BigDecimal(2000));
		assertNotNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void withdrawMoneyFailWithPhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.withdrawMoney("9703806", new BigDecimal(2000));
		assertNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void withdrawMoneyPassWithMoney() throws PaymentWalletException {
		Customer customer = paymentwalletservice.withdrawMoney("9703806806992", new BigDecimal(2000));
		assertNotNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void fundTransferFailWithsourcePhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.fundTransfer("9703806","8341219705", new BigDecimal(2000));
		assertNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void fundTransferFailWithsourcePhoneMaxNumbers() throws PaymentWalletException {
		Customer customer = paymentwalletservice.fundTransfer("9703806992456","8341219705", new BigDecimal(2000));
		assertNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void fundTransferFailWithTargetPhoneMaxNumbers() throws PaymentWalletException {
		Customer customer = paymentwalletservice.fundTransfer("9703806992","8341219705123", new BigDecimal(2000));
		assertNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void fundTransferFailWithTargetPhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.fundTransfer("9703806992","834121", new BigDecimal(2000));
		assertNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void fundTransferPassWithAllCorrect() throws PaymentWalletException {
		Customer customer = paymentwalletservice.fundTransfer("9703806992","8341219705", new BigDecimal(2000));
		assertNotNull(customer);
	}

	@Test(expected=PaymentWalletException.class)
	public void showBalanceFailWithPhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.showBalance("9703806");
		assertNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void showBalancePassWithPhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.showBalance("9703806992");
		assertNotNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void printStatementFailWithPhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.showBalance("9703806");
		assertNull(customer);
	}
	@Test(expected=PaymentWalletException.class)
	public void printStatementPassWithPhone() throws PaymentWalletException {
		Customer customer = paymentwalletservice.showBalance("9703806992");
		assertNotNull(customer);
	}
}
