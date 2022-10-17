import java.util.List;

public class Grafo {
    private final List<Vertice> vertices;
    private final List<Aresta> arestas;

    public Grafo(List<Vertice> vertexes, List<Aresta> arestas) {
        this.vertices = vertexes;
        this.arestas = arestas;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }



}