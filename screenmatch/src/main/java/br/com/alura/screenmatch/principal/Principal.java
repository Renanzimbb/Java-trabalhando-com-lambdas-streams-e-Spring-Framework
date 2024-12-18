package br.com.alura.screenmatch.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";

    private static final String API_KEY= "&apikey=785a333b";

    private ConsumoAPI consumo = new ConsumoAPI();
    
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){
        System.out.println("DIgite o nome da série para busca: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        ConverteDados conversor = new ConverteDados();
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    
        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i < dados.totalTemporadas(); i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

        // for(int i = 0; i < dados.totalTemporadas(); i++){
        //     List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
        //     for(int j = 0; j < episodiosTemporada.size(); j++){
        //         System.out.println((episodiosTemporada.get(j)).titulo());
        //     }
        // }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }        
}
