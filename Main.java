import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca bib = new Biblioteca();

        bib.cadastrarLivro("500", "1984", "George Orwell");
        bib.cadastrarLivro("300", "Codigo Limpo", "Robert C. Martin");
        bib.cadastrarLivro("700", "Estruturas de Dados", "Robert Lafore");
        bib.cadastrarLivro("200", "Harry Potter", "J.K. Rowling");
        bib.cadastrarLivro("400", "O Hobbit", "J.R.R. Tolkien");

        int op = -1;
        while (op != 0) {
            System.out.println("\n=== BIBLIOTECA PUBLICA ===");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Buscar livro por ISBN");
            System.out.println("3. Excluir livro");
            System.out.println("4. Listar acervo (In-Ordem, Pre-Ordem, Pos-Ordem)");
            System.out.println("5. Emprestar livro");
            System.out.println("6. Devolver livro");
            System.out.println("7. Listar fila de reservas");
            System.out.println("8. Listar pilha de devolucoes");
            System.out.println("9. Repor livro na estante");
            System.out.println("0. Sair");
            System.out.print("Opcao: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                op = -1;
            }

            switch (op) {
                case 1:
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    bib.cadastrarLivro(isbn, titulo, sc.nextLine());
                    break;
                case 2:
                    System.out.print("ISBN: ");
                    bib.buscarLivro(sc.nextLine());
                    break;
                case 3:
                    System.out.print("ISBN: ");
                    bib.excluirLivro(sc.nextLine());
                    break;
                case 4:
                    bib.exibirPercursos();
                    break;
                case 5:
                    System.out.print("ISBN: ");
                    String isbnEmp = sc.nextLine();
                    System.out.print("Leitor: ");
                    bib.emprestarLivro(isbnEmp, sc.nextLine());
                    break;
                case 6:
                    System.out.print("ISBN: ");
                    bib.devolverLivro(sc.nextLine());
                    break;
                case 7:
                    bib.listarReservas();
                    break;
                case 8:
                    bib.listarDevolucoes();
                    break;
                case 9:
                    bib.reporEstante();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
        sc.close();
    }
}