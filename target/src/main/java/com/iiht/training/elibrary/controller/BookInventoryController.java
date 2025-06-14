package com.iiht.training.elibrary.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

import com.iiht.training.elibrary.inventory.BookInventory;
import com.iiht.training.elibrary.model.Book;
import com.iiht.training.elibrary.exception.*;

public class BookInventoryController {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BookInventory inventory = new BookInventory();

            // Get number of books
            System.out.print("Enter number of books: ");
            int n = Integer.parseInt(br.readLine());

            // Add books
            for (int i = 0; i < n; i++) {
                try {
                    System.out.println("\nEnter details for book " + (i + 1));
                    
                    System.out.print("Enter Book ID: ");
                    Integer id = Integer.parseInt(br.readLine());
                    
                    System.out.print("Enter ISBN: ");
                    String isbn = br.readLine();
                    
                    System.out.print("Enter Name: ");
                    String name = br.readLine();
                    
                    System.out.print("Enter Publication: ");
                    String publication = br.readLine();
                    
                    System.out.print("Enter Stream: ");
                    String stream = br.readLine();

                    Book book = new Book(id, isbn, name, false, publication, stream);
                    
                    inventory.addBook(book);
                    System.out.println("Book added successfully!");
                    
                } catch (ISBNAlreadyExistsException e) {
                    System.out.println("Error: " + e.getMessage());
                    i--; // retry this book
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter valid number");
                    i--; // retry this book
                }
            }

            // Show current inventory
            System.out.println("\nCurrent Inventory:");
            displayBooks(inventory.showInventory());

            // Issue a book
            try {
                System.out.println("\nEnter details for book to issue:");
                
                System.out.print("Enter ISBN to issue: ");
                String isbnToIssue = br.readLine();
                
                System.out.print("Enter Student Name: ");
                String studentName = br.readLine();

                inventory.issueBook(isbnToIssue, studentName);
                System.out.println("Book issued successfully!");

            } catch (ISBNDoesNotExistsException | BookAlreadyIssuedException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Show remaining books
            System.out.println("\nRemaining Books in Stock:");
            List<Book> remainingBooks = inventory.showInventory();
            displayBooks(remainingBooks);

        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }

    private static void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available");
            return;
        }
        
        for (Book book : books) {
            if (!book.getIssued()) {
                System.out.println(book);
            }
        }
    }
}
