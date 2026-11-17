public class FilaReservas {
    private static class No {
        String isbn;
        String leitor;
        No proximo;

        No(String isbn, String leitor) {
            this.isbn = isbn;
            this.leitor = leitor;
        }
    }

    private No inicio;
    private No fim;

    public void inserir(String isbn, String leitor) {
        No novo = new No(isbn, leitor);
        if (fim != null) {
            fim.proximo = novo;
        }
        fim = novo;
        if (inicio == null) {
            inicio = novo;
        }
    }

    public String excluir() {
        if (inicio == null) {
            return null;
        }
        String info = inicio.leitor + " (ISBN: " + inicio.isbn + ")";
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        return info;
    }

    public String excluirPorIsbn(String isbn) {
        if (inicio == null) {
            return null;
        }
        if (inicio.isbn.equalsIgnoreCase(isbn)) {
            String leitor = inicio.leitor;
            excluir();
            return leitor;
        }
        No anterior = inicio;
        No atual = inicio.proximo;
        while (atual != null) {
            if (atual.isbn.equalsIgnoreCase(isbn)) {
                anterior.proximo = atual.proximo;
                if (atual == fim) {
                    fim = anterior;
                }
                return atual.leitor;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return null;
    }

    public boolean buscar(String isbn, String leitor) {
        No atual = inicio;
        while (atual != null) {
            if (atual.isbn.equalsIgnoreCase(isbn) && atual.leitor.equalsIgnoreCase(leitor)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public void listar() {
        if (inicio == null) {
            System.out.println("Fila de reservas vazia.");
            return;
        }
        No atual = inicio;
        int pos = 1;
        while (atual != null) {
            System.out.println(pos + "o -> Leitor: " + atual.leitor + " | ISBN: " + atual.isbn);
            atual = atual.proximo;
            pos++;
        }
    }
}