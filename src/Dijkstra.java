import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    private final List<Vertice> vertices;
    private final List<Aresta> arestas;
    private Set<Vertice> nodesPermanentes;
    private Set<Vertice> nodesProvisorios;
    private Map<Vertice, Vertice> anteriores;
    private Map<Vertice, Integer> distancia;

    public Dijkstra(Grafo graph) {
        this.vertices = new ArrayList<Vertice>(graph.getVertices());
        this.arestas = new ArrayList<Aresta>(graph.getArestas());
    }

    
    private void findDistanciasMinimas(Vertice node) {
        List<Vertice> adjacentNodes = getVizinhos(node);
        for (Vertice vertice : adjacentNodes) {
            if (getMenorDistancia(vertice) > getMenorDistancia(node)
                    + getDistancia(node, vertice)) {
                distancia.put(vertice, getMenorDistancia(node)
                        + getDistancia(node, vertice));
                anteriores.put(vertice, node);
                nodesProvisorios.add(vertice);
            }
        }

    }

    private int getDistancia(Vertice node, Vertice vertice) {
        for (Aresta edge : arestas) {
            if (edge.getOrigem().equals(node)
                    && edge.getDestino().equals(vertice)) {
                return edge.getPeso();
            }
        }
        throw new RuntimeException("Erro");
    }

    private List<Vertice> getVizinhos(Vertice node) {
        List<Vertice> vizinhos = new ArrayList<Vertice>();
        for (Aresta edge : arestas) {
            if (edge.getOrigem().equals(node)
                    && !isPermanente(edge.getDestino())) {
                vizinhos.add(edge.getDestino());
            }
        }
        return vizinhos;
    }

    private Vertice getMinimo(Set<Vertice> vertices) {
        Vertice min = null;
        for (Vertice vertex : vertices) {
            if (min == null) {
                min = vertex;
            } else {
                if (getMenorDistancia(vertex) < getMenorDistancia(min)) {
                    min = vertex;
                }
            }
        }
        return min;
    }

    private boolean isPermanente(Vertice vertex) {
        return nodesPermanentes.contains(vertex);
    }

    public int getMenorDistancia(Vertice destination) {
        Integer d = distancia.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    
    public LinkedList<Vertice> getCaminho(Vertice target) {
        LinkedList<Vertice> path = new LinkedList<Vertice>();
        Vertice step = target;
        // se a path existe
        if (anteriores.get(step) == null) {
            return null;
        }
        path.add(step);
        while (anteriores.get(step) != null) {
            step = anteriores.get(step);
            path.add(step);
        }
        // ordem
        Collections.reverse(path);
        return path;
    }
    
    public void execute(Vertice source) {
        nodesPermanentes = new HashSet<Vertice>();
        nodesProvisorios = new HashSet<Vertice>();
        distancia = new HashMap<Vertice, Integer>();
        anteriores = new HashMap<Vertice, Vertice>();
        distancia.put(source, 0);
        nodesProvisorios.add(source);
        while (nodesProvisorios.size() > 0) {
            Vertice node = getMinimo(nodesProvisorios);
            nodesPermanentes.add(node);
            nodesProvisorios.remove(node);
            findDistanciasMinimas(node);
        }
    }

}