package ifnmg.edu.com.book;

import ifnmg.edu.com.repository.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class BookDao extends Dao<Book> {
    public static final String TABLE = "book";
    @Override
    public String getFindByIdStatement() {
        return "select id, title, authors, acquisition, pages, year, edition, price from " + TABLE + " where id = ?" ;
    }

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(title, authors, acquisition, pages, year, edition, price)" + " values(?,?,?,?,?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " +  TABLE + " set title = ?, authors = ?, acquisition = ?, pages = ?, year = ?, edition = ?, price = ?"
                + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select * from " + TABLE;

    }

    @Override
    public String getDeleteStatement(){
        return "delete from " + TABLE + " where id = ?";
    }


    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Book e) {
        try{
            if(e.getTitle() != null){
                pstmt.setObject(1, e.getTitle(), Types.VARCHAR);
            }

            if(e.getAuthors() != null){
                pstmt.setObject(2, e.getAuthors(), Types.VARCHAR);
            }

            pstmt.setObject(3, e.getAcquisition(), Types.DATE);

            pstmt.setObject(4, e.getPages(), Types.SMALLINT);

            if(e.getYear() != null){
                pstmt.setObject(5, e.getYear(), Types.SMALLINT);
            }

            pstmt.setObject(6, e.getEdition(), Types.TINYINT);

            pstmt.setObject(7, e.getPrice(), Types.DECIMAL);

            if(e.getId() != null){
                pstmt.setObject(8, e.getId(), Types.BIGINT);
            }

        }catch (Exception ex){
            System.out.println("Exception in composeSave or Update: " + ex);
        }

    }

    @Override
    public Book extractObject(ResultSet rs) {

        Book queryBook = null;

        try{
            queryBook = new Book();

            queryBook.setId(rs.getLong("id"));
            queryBook.setTitle(rs.getString("title"));
            queryBook.setAuthors(rs.getString("authors"));
            queryBook.setAcquisition(rs.getDate("acquisition").toLocalDate());
            queryBook.setPages(rs.getShort("pages"));
            queryBook.setYear(rs.getShort("year"));
            queryBook.setEdition(rs.getByte("edition"));
            queryBook.setPrice(rs.getBigDecimal("price"));


        }catch (Exception ex){
            System.out.println("Exception in extractObject: " + ex);
        }


        return queryBook;
    }

    @Override
    public ArrayList<Book> extractObjects(ResultSet rs) {

        ArrayList<Book> queriesBook = new ArrayList<>();


        Book queryBook = null;
        try{
            while(rs.next()){
                queryBook = new Book();

                queryBook.setId(rs.getLong("id"));
                queryBook.setTitle(rs.getString("title"));
                queryBook.setAuthors(rs.getString("authors"));
                queryBook.setAcquisition(rs.getDate("acquisition").toLocalDate());
                queryBook.setPages(rs.getShort("pages"));
                queryBook.setYear(rs.getShort("year"));
                queryBook.setEdition(rs.getByte("edition"));
                queryBook.setPrice(rs.getBigDecimal("price"));
                queriesBook.add(queryBook);
            }

        }catch (Exception ex){
            System.out.println("Exception in extractObject: " + ex);
        }


        return queriesBook;
    }
}
