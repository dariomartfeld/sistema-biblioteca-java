public class ArvoreLivros {
    private static class No {
        Livro livro;
        No esquerda;
        No direita;

        No(Livro livro) {
            this.livro = livro;
        }
    }

    private No raiz;

    public boolean inserir(Livro livro) {
        if (buscar(livro.getIsbn()) != null) {
            return false;
        }
        raiz = inserirRec(raiz, livro);
        return true;
    }

    private No inserirRec(No atual, Livro livro) {
        if (atual == null) {
            return new No(livro);
        }
        int cmp = livro.getIsbn().compareToIgnoreCase(atual.livro.getIsbn());
        if (cmp < 0) {
            atual.esquerda = inserirRec(atual.esquerda, livro);
        } else if (cmp > 0) {
            atual.direita = inserirRec(atual.direita, livro);
        }
        return atual;
    }

    public Livro buscar(String isbn) {
        No atual = raiz;
        while (atual != null) {
            int cmp = isbn.compareToIgnoreCase(atual.livro.getIsbn());
            if (cmp == 0) {
                return atual.livro;
            }
            atual = (cmp < 0) ? atual.esquerda : atual.direita;
        }
        return null;
    }

    public boolean excluir(String isbn) {
        if (buscar(isbn) == null) {
            return false;
        }
        raiz = excluirRec(raiz, isbn);
        return true;
    }

    private No excluirRec(No atual, String isbn) {
        if (atual == null) {
            return null;
        }
        int cmp = isbn.compareToIgnoreCase(atual.livro.getIsbn());
        if (cmp < 0) {
            atual.esquerda = excluirRec(atual.esquerda, isbn);
        } else if (cmp > 0) {
            atual.direita = excluirRec(atual.direita, isbn);
        } else {
            if (atual.esquerda == null) {
                return atual.direita;
            }
            if (atual.direita == null) {
                return atual.esquerda;
            }
            No menor = atual.direita;
            while (menor.esquerda != null) {
                menor = menor.esquerda;
            }
            atual.livro = menor.livro;
            atual.direita = excluirRec(atual.direita, menor.livro.getIsbn());
        }
        return atual;
    }

    public void percursos() {
        if (raiz == null) {
            System.out.println("Acervo vazio.");
            return;
        }
        System.out.println("\n--- IN-ORDEM ---");
        inOrdem(raiz);
        System.out.println("\n--- PRE-ORDEM ---");
        preOrdem(raiz);
        System.out.println("\n--- POS-ORDEM ---");
        posOrdem(raiz);
    }

    private void inOrdem(No no) {
        if (no != null) {
            inOrdem(no.esquerda);
            System.out.println(no.livro);
            inOrdem(no.direita);
        }
    }

    private void preOrdem(No no) {
        if (no != null) {
            System.out.println(no.livro);
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    private void posOrdem(No no) {
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.println(no.livro);
        }
    }
}