package processoSelecao.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.ws.Holder;

import processoSelecao.controller.ReservaHotel;
import processoSelecao.model.Hotel;

public class RealizaReserva {

	public static void main(String[] args) throws IOException {
		
		ArrayList<Hotel> hoteis = new ArrayList();
		ReservaHotel reserva = new ReservaHotel();
		Hotel saida = new Hotel();
		String melhorHotel;
		
		/**
		 * Guarda o nome do arquivo de entrada do programa
		 */
		String nomeArquivoEntrada = "files/entrada.txt";

		/**
		 * Instancia os hoteis
		 */
		Hotel h1 = new Hotel("Lakewood", 3, 110.0, 90.0, 80.0, 80.0);
		Hotel h2 = new Hotel("Bridgewood", 4, 160, 60.0, 110.0, 50.0);
		Hotel h3 = new Hotel("Ridgewood", 5, 220.0, 150.0, 100.0, 40.0);

		/**
		 * Adicioa os hoteis em um arrayList para ser usado na busca do melhor hotel
		 */
		hoteis.add(h1);
		hoteis.add(h2);
		hoteis.add(h3);
		
		saida = reserva.buscaMelhorHotel(nomeArquivoEntrada, hoteis);
		melhorHotel = saida.getNome();
		
		System.out.println(melhorHotel);
		
	}

}
