package processoSelecao.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import processoSelecao.model.Hotel;

public class ReservaHotel {

	/**
	 * Le os dados do arquivo.txt para transforma-lo em string para ser manipulada pelo programa
	 * @param txt - arquivo txt com as entradas dos dados
	 * @return string - string contendo a entrada do programa
	 * @throws IOException
	 */
	private String leEntrada(String txt) throws IOException{ 
			File file = new File(txt);

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			
			String entrada = "";
			try{
				while(br.ready()){
					sb.append(br.readLine());
				}
				entrada = sb.toString();
				br.close();
				fr.close();
			} catch (IOException e) {
				System.out.println("Erro na leitura do arquivo");
			}
			return entrada;
	 }
	
	/**
	 * Verifica se o cliente e ou nao vip
	 * @param partes - string que vem antes dos : na entrada e que diz se o cliente e vip ou comum
	 * @return verdadeiro ou falso
	 */
	private boolean verificaTipoCliente(String partes[]){
		String vip = "Rewards";
		return (partes[0].equals(vip)) ? true : false;
	}
	
	/**
	 * Pega a informacao entre () na entrada do programa e verifica quais dias sao dias da semana e fim de semana
	 * @param partes - string que vem depois dos : na entrada 
	 * @return arraylist - array com valor S caso seja fim de semana e N caso seja dia da semana
	 */
	private ArrayList<String> verificaDiasHospedagem(String partes[]){
		String []descricoes = partes[1].split(",");
		ArrayList<String> diasHospedagem = new ArrayList<String>();
		
		for (String descricao : descricoes){
			Pattern p = Pattern.compile("\\((.*?)\\)", Pattern.DOTALL);
		    Matcher matcher = p.matcher(descricao);
	        while(matcher.find()){
	        	if(matcher.group(1).equals("sat") || matcher.group(1).equals("sun"))
	        		diasHospedagem.add("S");
	        	else 
	        		diasHospedagem.add("N");
	        }
		} 
		return diasHospedagem;
	}
	
	/**
	 * Calcula o valor da estadia partindo do arraylist contendo S e N, do hotel e do tipo de cliente
	 * @param clienteVip - valor boolean com true para cliente vip e false para cliente comum
	 * @param diasHospedagem - array contendo os dias da hospedagem classificados entre fim de semana ou dia da semana "S ou N
	 * @param hotel - Hotel cadasdrado na lista de hoteis
	 * @return double valor do calculo da estadia
	 */
	private double calculaEstadia(boolean clienteVip, ArrayList<String> diasHospedagem, Hotel hotel){
		double valorEstadia = 0;
		if(clienteVip == true){
			for(int i = 0; i<diasHospedagem.size(); i++){
				if(diasHospedagem.get(i).equals("N")){
					valorEstadia += hotel.getPrecoSemanaVip();
				} else{
					valorEstadia += hotel.getPrecoFimSemanaVip();
				}
			}
		} else {
			for(int i = 0; i<diasHospedagem.size(); i++){
				if(diasHospedagem.get(i).equals("N")){
					valorEstadia += hotel.getPrecoSemanaComum();
				} else{
					valorEstadia += hotel.getPrecoFimSemanaComum();
				}
			}
		}
		return valorEstadia;
	}
	
	/**
	 * Monta os paremetros para os outros metodos e realiza a verificacao de qual e o mais barato e desempata de acordo com as estrelas se for necessario
	 * @param nomeEntrada - nome do arquivo txt
	 * @param hoteis - array de objetos do tipo Hotel
	 * @return hotel - o melhor hotel de acordo com o menor preco e maior numero de estrelas
	 * @throws IOException
	 */
	public Hotel buscaMelhorHotel(String nomeEntrada, ArrayList<Hotel> hoteis) throws IOException{
		ReservaHotel reserva = new ReservaHotel();
		boolean cliente = true;
		ArrayList<String> diasHospedagem = new ArrayList<String>();
		Hotel hotelAuxiliar = hoteis.get(0), melhorHotel= hoteis.get(0);
		String entrada = reserva.leEntrada(nomeEntrada);
		String partes[] = entrada.split(":");

		diasHospedagem = verificaDiasHospedagem(partes);
		cliente = verificaTipoCliente(partes);
		
		double precoEstadia = calculaEstadia(cliente, diasHospedagem, hoteis.get(0));
		double estadia = 0;

		for(int i=0; i<hoteis.size(); i++){
			estadia = calculaEstadia(cliente, diasHospedagem, hoteis.get(i));
			if(precoEstadia > estadia){
				precoEstadia = estadia;
				melhorHotel = hoteis.get(i);
			}else if(precoEstadia == estadia){
				if(hoteis.get(i).getEstrelas() > hotelAuxiliar.getEstrelas()){
					melhorHotel = hoteis.get(i);
				} else{
					melhorHotel = hotelAuxiliar;
				}
			} 
		}
		
		return melhorHotel;
	}
}
