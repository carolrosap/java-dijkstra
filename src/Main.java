
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

	private static List<Vertice> nodes;
	private static List<Aresta> edges;

	public static void readFile() throws IOException {
		FileReader arquivo = new FileReader(
				new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "custos_totais.csv"));
		BufferedReader leitura = new BufferedReader(arquivo);

		String linha = leitura.readLine();

		nodes = new ArrayList<Vertice>();
		edges = new ArrayList<Aresta>();

		int cont = 0;

		while (linha != null) {
			String[] novaLinha = linha.split(",");
			int origem = Integer.valueOf(novaLinha[0]);
			int destino = Integer.valueOf(novaLinha[1]);
			int distancia = Integer.valueOf(novaLinha[2]);

			Vertice vOrigem = new Vertice(String.valueOf(origem), "Vertice_" + origem);
			Vertice vDestino = new Vertice(String.valueOf(destino), "Vertice_" + destino);
			if (!nodes.contains(vOrigem)) {
				nodes.add(vOrigem);
			}
			if (!nodes.contains(vDestino)) {
				nodes.add(vDestino);
			}

			Aresta lane = new Aresta("Aresta_" + cont, vOrigem, vDestino, distancia);

			edges.add(lane);
			cont++;

			linha = leitura.readLine();
		}
		arquivo.close();
	}

	public static void main(String[] args) throws IOException {

		readFile();

		Grafo graph = new Grafo(nodes, edges);

		Dijkstra dijkstra = new Dijkstra(graph);

		Vertice origem = new Vertice("1", "Vertice_1");
		Vertice destino = new Vertice("100", "Vertice_100");

		dijkstra.execute(origem);
		LinkedList<Vertice> path = dijkstra.getCaminho(destino);
		System.out.println("Caminho mínimo:");
		for (Vertice vertex : path) {
			System.out.println("> " + vertex);
		}
		int minimo = dijkstra.getMenorDistancia(destino);
		System.out.println("Distância mínima: " + minimo);
	}

}