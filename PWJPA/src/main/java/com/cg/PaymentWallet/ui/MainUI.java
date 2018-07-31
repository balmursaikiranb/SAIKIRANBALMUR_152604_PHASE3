
package com.cg.PaymentWallet.ui;

import java.math.BigDecimal;
import java.util.Scanner;

import com.cg.PaymentWallet.dto.dto.Wallet;
import com.cg.PaymentWallet.exception.PaymentWalletException;
import com.cg.PaymentWallet.service.IPaymentsWalletService;
import com.cg.PaymentWallet.service.PaymentWalletServiceImpl;
//import com.cg.paymentwallet.dto.dto.Customer;
import com.cg.paymentwallet.service.PaymentWalletService;
import com.cg.PaymentWallet.dto.dto.*;

public class MainUI {
	public static void main(String args[]) {
		details();

	}

	private static void details() {
		IPaymentsWalletService paymentwalletservice = new PaymentWalletServiceImpl();
		int choice = 0;
		boolean error = false;
		String money;
		String ch;
		String number;
		do {
			System.out.println("1. register");
			System.out.println("2.login");
			System.out.println("3.exit");
			Scanner sc1 = new Scanner(System.in);
			System.out.println("enter the choice:");
			choice = sc1.nextInt();
			switch (choice) {
			case 1:
				Scanner scanner = new Scanner(System.in);

				try {
					Wallet wallet = new Wallet();
					Customer customer = new Customer();
					System.out.println("enter phone number");
					String phone = scanner.next();
					error = paymentwalletservice.validatePhone(phone);
					if (error) {
						customer.setPhoneNumber(phone);
						System.out.println("enter name");
						String name = scanner.next();
						error = paymentwalletservice.validateName(name);
						if (error) {
							customer.setName(name);

							System.out.println("enter email");
							String email = scanner.next();
							error = paymentwalletservice.validateEmail(email);
							if (error) {
								customer.setEmailId(email);
								System.out.println("enter password");
								String password = sc1.next();
								error = paymentwalletservice.validatePassword(password);
								if (error) {
									customer.setStatement("zzz");
									wallet.setPassword(password);
									wallet.setBalance(new BigDecimal(0));
									customer.setWallet(wallet);

									Customer cust = paymentwalletservice.registerCustomer(customer);
									System.out.println(cust.toString());
								}
							}
						}
					}
				} catch (PaymentWalletException paymentwalletexception) {
					System.out.println(paymentwalletexception.getMessage());
				}

				break;
			case 2:
				try {
					System.out.println("enter phone number");
					number = sc1.next();
					error = paymentwalletservice.validatePhone(number);
					if (error) {
						System.out.println("enter password");
						String password = sc1.next();
						error = paymentwalletservice.validatePassword(password);
						if (error) {
							error = paymentwalletservice.checkLogin(number, password);

							if (error) {

								do {

									System.out.println("1. withdraw amount");
									System.out.println("2.deposit amount");
									System.out.println("3.fund transfer");
									System.out.println("4. show balance");
									System.out.println("5.print transactions");
									System.out.println("6.logout");
									Scanner sc = new Scanner(System.in);
									System.out.println("enter the choice:");
									choice = sc.nextInt();
									switch (choice) {

									case 1:

										try {

											System.out.println("enter balance");
											BigDecimal balance1 = sc.nextBigDecimal();
											error = paymentwalletservice.validateMoney(balance1);
											if (error) {

												com.cg.PaymentWallet.dto.dto.Customer cust3 = paymentwalletservice
														.withdrawMoney(number, balance1);
												System.out.println(cust3.toString());
											}

										} catch (PaymentWalletException e) {

											System.out.println(e.getMessage());
										}

										break;
									case 2:

										try {

											System.out.println("enter balance");
											BigDecimal balance2 = sc.nextBigDecimal();
											error = paymentwalletservice.validateMoney(balance2);
											if (error) {
												com.cg.PaymentWallet.dto.dto.Customer cust2;
												cust2 = paymentwalletservice.depositMoney(number, balance2);
												System.out.println(cust2.toString());
											}

										} catch (PaymentWalletException e) {

											System.out.println(e.getMessage());
										}

										break;
									case 3:

										try {

											System.out.println("enter targer phone number");
											String phone4 = sc.next();
											error = paymentwalletservice.validatePhone(phone4);
											if (error) {
												System.out.println("enter balance");
												BigDecimal balance3 = sc.nextBigDecimal();
												error = paymentwalletservice.validateMoney(balance3);
												if (error) {

													com.cg.PaymentWallet.dto.dto.Customer cust4 = paymentwalletservice
															.fundTransfer(number, phone4, balance3);
													System.out.println(cust4.toString());
												}
											}

										} catch (PaymentWalletException e) {

											System.out.println(e.getMessage());
										}

										break;
									case 4:
										try {

											com.cg.PaymentWallet.dto.dto.Customer cust;
											cust = paymentwalletservice.showBalance(number);

											System.out.println(cust.toString());

										} catch (PaymentWalletException e) {
											// TODO Auto-generated catch block
											System.out.println(e.getMessage());
										}
										break;
									case 5:
										try {

											String result;
											result = paymentwalletservice.printTransaction(number);

											System.out.println("Your Transactions Are:\n");
											Scanner scanner2 = new Scanner(result).useDelimiter("zzz");
											while (scanner2.hasNext()) {
												String str = scanner2.next();
												System.out.println(str);
											}

										} catch (PaymentWalletException e) {

											System.out.println(e.getMessage());
										}
										break;
									case 6:
										System.out.println(" you are logged out thankyou for using");

										details();
										break;
									default:
										System.out.println("enter correct value");
									}
									System.out.println("enter y or yes to continue and n or no to exit");
									ch = sc.next();
								} while ((ch.equalsIgnoreCase("y")) || ch.equalsIgnoreCase("yes"));
							}

							else {
								System.out.println("please enter correct details");
							}

						}
					}

				} catch (PaymentWalletException paymentwalletexception) {
					System.out.println(paymentwalletexception.getMessage());
				}

				break;
			case 3:
				System.out.println("thankyou for using");
				System.exit(0);
				break;
			}
			System.out.println("enter y or yes to continue and n or no to exit");
			ch = sc1.next();
		} while ((ch.equalsIgnoreCase("y")) || ch.equalsIgnoreCase("yes"));

	}
}
