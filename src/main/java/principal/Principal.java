package principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

public class Principal {
	
	private Scanner leitura = new Scanner(System.in);
	private ConsumoApi consumo = new ConsumoApi();
	private ConverteDados conversor = new ConverteDados();
	
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=6585022c";
	
	public void exibeMenu() {
		System.out.println("Digite o nome da série para busca: ");
		var nomeSerie = leitura.nextLine();
		var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		
		
		List<DadosTemporada> temporadas = new ArrayList<>();
		
		for (int i = 1; i<=dados.TotalTemporadas(); i++) {
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);			
		}
		temporadas.forEach(System.out::println);
		
		/* Mateus Victorio, 20241121, Code below commented because it was replaced by the next line with lambda expression
		 * for (int i = 0; i < dados.TotalTemporadas(); i++) {
			List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
			for (int j = 0; j < episodiosTemporada.size(); j++) {
				System.out.println(episodiosTemporada.get(j).titulo());
			}
		}*/
		
		temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
		//temporadas.forEach(System.out::println);
		
		List<String> nomes = Arrays.asList("Jacque","Iasmin","Paulo","Rogrigo","Nico");
		
		//The "Stream" statement allow to use a lot of others functions.
		//In the example below the follow code only order the list and print it.
		nomes.stream().sorted().forEach(System.out::println);
		
		//In this example, it is ordering and showing the first three items of list.
		nomes.stream().sorted().limit(3).forEach(System.out::println);
		
		nomes.stream() //"Stream" to use others functions
			 .sorted() //"sorted" to order the list
			 .limit(3) //"limit" to show only three values 
			 .filter(n -> n.startsWith("N")) //"filter" to do any logic in the lambda expression, in this case to show only names starts with "N"
			 .map(n -> n.toUpperCase()) //"map" to change the value
			 .forEach(System.out::println); //"forEach" to execute the print to each item on the list.
	}
}
