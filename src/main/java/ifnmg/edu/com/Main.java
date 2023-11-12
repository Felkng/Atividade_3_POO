package ifnmg.edu.com;

import ifnmg.edu.com.book.Book;
import ifnmg.edu.com.book.BookDao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            //Criando livro com data posterior
            Book book0 = new Book(null,
                    "Livro com data posterior",
                    "Autor desconhecido",
                    LocalDate.now().plusDays((long) 3),
                    (short) 666,
                    (short) 2020, (byte) 7,
                    BigDecimal.valueOf(10.99));
            long book0Id = new BookDao().saveOrUpdate(book0);
            book0.setId(book0Id);
        }catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        try{
            //Criando 3 objetos dos livros e salvando eles no Banco de dados
            Book book1 = new Book(null,
                    "Game of Thrones",
                    "George R. R. Martin",
                    LocalDate.of(1996,8,1),
                    (short)600,
                    (short)2019,(byte)6,
                    BigDecimal.valueOf(43.99));
            long book1Id = new BookDao().saveOrUpdate(book1);
            book1.setId(book1Id);

            Book book2 = new Book(null,
                    "O Hobbit",
                    "J.R.R. Tolkien",
                    LocalDate.of(1937,7,21),
                    (short)336,
                    (short)2021,(byte)4,
                    BigDecimal.valueOf(79.59));
            long book2Id = new BookDao().saveOrUpdate(book2);
            book2.setId(book2Id);

            Book book3 = new Book(null,
                    "1984",
                    "George Orwell",
                    LocalDate.of(1949,6,8),
                    (short)480,
                    (short)2009,(byte)1,
                    BigDecimal.valueOf(34.90));
            long book3Id = new BookDao().saveOrUpdate(book3);
            book3.setId(book3Id);

            //Editando livro
            Book bookEdited = new BookDao().findById(book1Id);
            bookEdited.setTitle("Game of Thrones EDITED");
            new BookDao().saveOrUpdate(bookEdited);

            //Procurando só um livro
            Book especicBook = new BookDao().findById(book2.getId());
            System.out.println("Livro recuperado pelo id ( " + book2.getId() + " ): "
                    + especicBook.getTitle());
            //Procurando só um livro
            especicBook = new BookDao().findById(book3.getId());
            System.out.println("Livro recuperado pelo id ( " + book3.getId() + " ): "
                    + especicBook.getTitle());

            //Recuperar todos os livros
            ArrayList<Book> bookList = new BookDao().findAll();
            System.out.println("Lista com todos os livros: ");
            for (var x : bookList){
                System.out.println(x.getTitle());
            }

            //Apagando livro
            System.out.println("Apagando livro com id: " + book1.getId());
            new BookDao().delete(book1.getId());
            ArrayList<Book> bookListErased = new BookDao().findAll();
            System.out.println("Lista com todos os livros: ");
            for (var x : bookListErased){
                System.out.println(x.getTitle());
            }

        }catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }

    }


}