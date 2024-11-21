package principal;

import java.util.ArrayList;
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
		temporadas.forEach(System.out::println);
		
	}
}
