package br.com.foursys.fourcamp.fourstore.communication;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.foursys.fourcamp.fourstore.controller.ProductController;
import br.com.foursys.fourcamp.fourstore.controller.TransactionController;

public class MainMenu {
	Integer opcaoDigitada;
	Scanner sc = new Scanner(System.in);
	ProductController productController;

	public void displaysMainMenu() {
		while (true) {
			System.out.print("##--------------MENU FOURSTORE--------------##\n");
			System.out.print("|--------------------------------------------|\n");
			System.out.print("| Op��o 1 - Cadastrar produto novo           |\n");
			System.out.print("| Op��o 2 - Registrar adi��o no estoque      |\n");
			System.out.print("| Op��o 3 - Listar todos os produtos         |\n");
			System.out.print("| Op��o 4 - Registrar remo��o de estoque     |\n");
			System.out.print("| Op��o 5 - Efetuar venda                    |\n");
			System.out.print("| Op��o 6 - Listar hist�rico de vendas       |\n");
			System.out.print("| Op��o 0 - Sair                             |\n");
			System.out.print("|--------------------------------------------|\n");
			System.out.print("|              Digite uma op��o:             |\n");
			System.out.print("|--------------------------------------------|\n");
			opcaoDigitada = sc.nextInt();

			switch (opcaoDigitada) {
			case 1:
				userCallCreate();
				break;
			case 2:
				userCallAdd();
				break;

			case 3:
				userCallListAll();
				break;
  
			case 4:
				userCallRemove();
				break;

			case 5:
				userCallSell();
				break;
				
			case 6:
				userCallHistory();
				break;

			case 0:
				System.out.println("Obrigado por utilizar nosso sistema!");
				System.exit(0);
				break;
			}

		}
	}

	public void userCallCreate() {
		System.out.println("Informe  a sku do produto:");
		String sku = sc.next();
		
		
		while(sku.length() != 12) {
			if(sku.length()  < 12) {
				System.out.println("O SKU esta com tamanho menor que o esperado, tamanho SKU:"+sku.length());
			}else {
				System.out.println("O SKU esta com tamanho maior que o esperado, tamanho SKU:"+sku.length());
			}
			System.out.println("O SKU deve ter tamanho 12 caracteres");
			System.out.println("Informe  a sku do produto:");
			sku = sc.next();
		}
		
		
		System.out.println("Informe  o pre�o do produto:");
		Double price = sc.nextDouble();

		System.out.println("Informe  a quantidade do produto:");
		Integer qtt = sc.nextInt();

		/*System.out.println("Informe  o tipo do produto:");
		String type = sc.next();

		System.out.println("Informe  o tamanho do produto:");
		String size = sc.next();

		System.out.println("Informe  a cor do produto:");
		String color = sc.next();

		System.out.println("Informe  a categoria do produto:");
		String category = sc.next();

		System.out.println("Informe  o departamento do produto:");
		String department = sc.next(); */

		productController = new ProductController();
		System.out.println(productController.addProduct(sku, price, qtt));

	}

	public void userCallAdd() {
		System.out.println("Informe a SKU do produto: ");
		sc.nextLine();
		String sku = sc.nextLine();

		System.out.println("Informe a quantidade a adicionar ao estoque: ");
		Integer qtt = sc.nextInt();
		productController = new ProductController();
		System.out.println(productController.callAdd(sku, qtt));

	}

	public void userCallListAll() {
		productController = new ProductController();
		System.out.println(productController.callListAll());
	}

	public void userCallRemove() {
		System.out.println("Informe a SKU do produto:");
		sc.nextLine();
		String sku = sc.nextLine();

		System.out.println("Informe a quantidade a reduzir do estoque: ");
		Integer qtt = sc.nextInt();
		productController = new ProductController();
		System.out.println(productController.callRemove(sku, qtt));
	}

	public void userCallSell() {
		String paymentData = null;
		int continuarAdicionandoProduto = 1;
		String sku = null;
		Integer quantidade = null;
		Map<String, Integer> quantidadeDeProdutos = new HashMap<String, Integer>();
	    
		while(continuarAdicionandoProduto == 1 ) {
			System.out.println("Informe o SKU do produto que deseja comprar: ");
			sc.nextLine();
			sku = sc.nextLine();

			System.out.println("Informe a quantidade que deseja comprar: ");
			quantidade = sc.nextInt();
			
			quantidadeDeProdutos.put(sku,quantidade);
			
			System.out.println("Deseja continhar Adicionando Produtos ? 1 - Para SIM / 0 - PARA NAO");
			continuarAdicionandoProduto = sc.nextInt();
		}
		
		System.out.println("Informe seu CPF ou digite 0 para continuar: ");
		String CPF = sc.next();
		
		productController.getProductPrice(quantidadeDeProdutos);
		
		System.out.println("\n Informe o m�todo de pagamento:\n"
				+ "1- Dinheiro � vista (10% de desconto)\n"
				+ "2- Cart�o de D�bito (5% de acr�scimo)\n"
				+ "3- Cart�o de Cr�dito (5% de acr�scimo);\n"
				+ "4- Pix (10% de desconto)");
		
		Integer paymentMethod = sc.nextInt();
		
		//criar m�todos para pegar cada pagamento
		TransactionController transactionController = new TransactionController();
		
		//mostra na tela pro usu�rio digitar dados do cart�o pix etc
		if(transactionController.getPaymentData(paymentMethod).equals("erro")) {
			System.out.println("M�todo de pagamento inv�lido.");
			return;
		}else {
			System.out.println(transactionController.getPaymentData(paymentMethod));
			paymentData = sc.next();
		}
		
		System.out.println(transactionController.sell(quantidadeDeProdutos, CPF, paymentMethod, paymentData));
		
	}
	
	
	public void userCallHistory() {
		TransactionController transactionController = new TransactionController();
		System.out.println(transactionController.returnSellHistory());
	}

}
