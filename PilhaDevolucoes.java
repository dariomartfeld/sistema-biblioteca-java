public class PilhaDevolucoes {
    private static class No {
        Livro livro;
        No proximo;

        No(Livro livro) {
            this.livro = livro;
        }
    }

    private No topo;

    public void inserir(Livro livro) {
        No novo = new No(livro);
        novo.proximo = topo;
        topo = novo;
    }

    public Livro excluir() {
        if (topo == null) {
            return null;
        }
        Livro removido = topo.livro;
        topo = topo.proximo;
        return removido;
    }

    public Livro buscar(String isbn) {
        No atual = topo;
        while (atual != null) {
            if (atual.livro.getIsbn().equalsIgnoreCase(isbn)) {
                return atual.livro;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public void listar() {
        if (topo == null) {
            System.out.println("Pilha de devolucoes vazia.");
            return;
        }
        No atual = topo;
        System.out.println("[TOPO]");
        while (atual != null) {
            System.out.println(" -> ISBN: " + atual.livro.getIsbn() + " | " + atual.livro.getTitulo());
            atual = atual.proximo;
        }
    }
}