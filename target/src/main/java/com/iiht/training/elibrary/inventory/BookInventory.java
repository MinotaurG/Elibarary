package com.iiht.training.elibrary.inventory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.iiht.training.elibrary.exception.BookAlreadyIssuedException;
import com.iiht.training.elibrary.exception.ISBNAlreadyExistsException;
import com.iiht.training.elibrary.exception.ISBNDoesNotExistsException;
import com.iiht.training.elibrary.model.Book;
import com.iiht.training.elibrary.model.BookIssue;

public class BookInventory {

    public List<Book> books = new ArrayList<>();
    public List<BookIssue> issuedBooks = new ArrayList<>();

    public boolean addBook(Book book) {
        if (book == null || book.getIsbn() == null) {
            return false;
        }

        for (Book existingBook : books) {
            if (existingBook.getIsbn().equals(book.getIsbn())) {
                throw new ISBNAlreadyExistsException("The ISBN " + book.getIsbn() + " already exists");
            }
        }

        return books.add(book);
    }

    public boolean issueBook(String isbn, String studentName) {
        if (isbn == null || studentName == null) {
            return false;
        }

        Book bookToIssue = null;
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                bookToIssue = book;
                break;
            }
        }

        if (bookToIssue == null) {
            throw new ISBNDoesNotExistsException("ISBN " + isbn + " does not exists");
        }

        // Check in issuedBooks list
        for (BookIssue issuedBook : issuedBooks) {
            if (issuedBook.getIsbn().equals(isbn)) {
                throw new BookAlreadyIssuedException("The Book is already Issued");
            }
        }

        BookIssue bookIssue = new BookIssue();
        bookIssue.setId(issuedBooks.size() + 1);
        bookIssue.setIsbn(isbn);
        bookIssue.setStudentName(studentName);
        bookIssue.setIssueDate(LocalDate.now());

        return issuedBooks.add(bookIssue);
    }

    public List<Book> showInventory() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            boolean isIssued = false;
            for (BookIssue issuedBook : issuedBooks) {
                if (book.getIsbn().equals(issuedBook.getIsbn())) {
                    isIssued = true;
                    break;
                }
            }
            if (!isIssued) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<BookIssue> showIssuedBooks() {
        return new ArrayList<>(issuedBooks);
    }
}
