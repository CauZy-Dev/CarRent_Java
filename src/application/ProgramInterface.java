package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class ProgramInterface {

	public static void main(String[] args) {
		// Interface faz um contrato com as classes = baixo acoplamento e flexivel
		
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do Carro: ");
		String carModel = scan.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm) ");
		LocalDateTime start = LocalDateTime.parse(scan.nextLine(),fmt);
		System.out.print("Retorno (dd/MM/yyyy hh:mm) ");
		LocalDateTime finish = LocalDateTime.parse(scan.nextLine(),fmt);
		
		CarRental cr = new  CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Entre com o preço por hora:");
		double pricePerHour = scan.nextDouble();
		System.out.print("Entre com o preço por dia:");
		double pricePerDay = scan.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService() );
		
		rentalService.processInvoice(cr);
		
		System.out.println("FATURA:  ");
		System.out.println("Pagamento básico: "  + cr.getInvoice().getBasicPayment());
		System.out.println("Pagamento Imposto: "  + cr.getInvoice().getTax());
		System.out.println("Pagamento total: "  + cr.getInvoice().getTotalPayment());


		
		scan.close();
	}

}
