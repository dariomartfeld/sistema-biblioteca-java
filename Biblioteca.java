public class Biblioteca {
    private ArvoreLivros acervo = new ArvoreLivros();
    private FilaReservas filaReservas = new FilaReservas();
    private PilhaDevolucoes pilhaDevolucoes = new PilhaDevolucoes();

    public void cadastrarLivro(String isbn, String titulo, String autor) {
        if (acervo.inserir(new Livro(isbn, titulo, autor))) {
            System.out.println("Livro cadastrado.");
        } else {
            System.out.println("ISBN ja existe.");
        }
    }

    public void buscarLivro(String isbn) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            System.out.println("Livro nao encontrado.");
            return;
        }
        System.out.println(livro);
        if (pilhaDevolucoes.buscar(isbn) != null) {
            System.out.println("Aviso: aguardando reposicao na pilha de devolucoes.");
        }
    }

    public void excluirLivro(String isbn) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null || livro.isEmprestado()) {
            System.out.println("Nao foi possivel excluir (inexistente ou emprestado).");
            return;
        }
        acervo.excluir(isbn);
        System.out.println("Livro excluido.");
    }

    public void exibirPercursos() {
        acervo.percursos();
    }

    public void emprestarLivro(String isbn, String leitor) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null) {
            System.out.println("Livro nao encontrado.");
            return;
        }
        if (!livro.isEmprestado()) {
            livro.setLeitorAtual(leitor);
            System.out.println("Emprestado para " + leitor + ".");
        } else if (filaReservas.buscar(isbn, leitor)) {
            System.out.println("Leitor ja esta na fila para este livro.");
        } else {
            filaReservas.inserir(isbn, leitor);
            System.out.println("Livro ocupado. " + leitor + " entrou na fila de reserva.");
        }
    }

    public void devolverLivro(String isbn) {
        Livro livro = acervo.buscar(isbn);
        if (livro == null || !livro.isEmprestado()) {
            System.out.println("Livro invalido ou nao esta emprestado.");
            return;
        }
        pilhaDevolucoes.inserir(livro);
        String proximoLeitor = filaReservas.excluirPorIsbn(isbn);
        if (proximoLeitor != null) {
            livro.setLeitorAtual(proximoLeitor);
            System.out.println("Devolvido e repassado ao proximo da fila: " + proximoLeitor + ".");
        } else {
            livro.setLeitorAtual(null);
            System.out.println("Devolvido e adicionado a pilha para reposicao.");
        }
    }

    public void reporEstante() {
        Livro topo = pilhaDevolucoes.excluir();
        System.out.println(topo == null ? "Pilha vazia." : "Reposto na estante: " + topo.getTitulo());
    }

    public void listarReservas() {
        filaReservas.listar();
    }

    public void listarDevolucoes() {
        pilhaDevolucoes.listar();
    }
}