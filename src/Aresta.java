public class Aresta  {
    private final String id;
    private final Vertice origem;
    private final Vertice destino;
    private final int peso;

    public Aresta(String id, Vertice origem, Vertice destino, int peso) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public String getId() {
        return id;
    }
    public Vertice getDestino() {
        return destino;
    }

    public Vertice getOrigem() {
        return origem;
    }
    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return origem + " " + destino;
    }


}