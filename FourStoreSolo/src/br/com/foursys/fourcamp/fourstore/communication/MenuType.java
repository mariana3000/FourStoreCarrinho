package br.com.foursys.fourcamp.fourstore.communication;

import java.util.Scanner;

import br.com.foursys.fourcamp.fourstore.enums.TypeProduct;

public class MenuType {
	public static void main(String[] args) {
		TypeProduct choice = null;

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Selecione a op��o desejada>>>"
				+"\n 1-VER�O"
				+"\n 2-INVERNO"
				+"\n 3-OUTONO"
				+"\n 4-PRIMAVERA"
				+"\n 5-OUTROS");
		
		int forma = sc.nextInt();
		
		switch (forma) {
		
		case 1:
			choice = TypeProduct.SU;
			break;
		case 2:
			choice = TypeProduct.WI;
			break;
		case 3:
			choice = TypeProduct.AU;
			break;
		case 4:
			choice = TypeProduct.SP;
			break;
		case 5:
			choice = TypeProduct.OT;
			break;
		}
		
		
		sc.close();

	}
}

