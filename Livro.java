public class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private String leitorAtual;

    public Livro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.leitorAtual = null;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getLeitorAtual() { return leitorAtual; }
    public void setLeitorAtual(String leitorAtual) { this.leitorAtual = leitorAtual; }
    public boolean isEmprestado() { return leitorAtual != null; }

    @Override
    public String toString() {
        String status = isEmprestado() ? "Emprestado (" + leitorAtual + ")" : "Disponivel";
        return "ISBN: " + isbn + " | " + titulo + " - " + autor + " | " + status;
    }
}