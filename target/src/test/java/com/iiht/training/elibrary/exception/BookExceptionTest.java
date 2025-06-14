package com.iiht.training.elibrary.exception;

import static com.iiht.training.elibrary.testutils.TestUtils.currentTest;
import static com.iiht.training.elibrary.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.elibrary.testutils.TestUtils.testReport;
import static com.iiht.training.elibrary.testutils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.iiht.training.elibrary.inventory.BookInventory;
import com.iiht.training.elibrary.model.Book;
import com.iiht.training.elibrary.testutils.MasterData;

class BookExceptionTest {

	static BookInventory inventory;

	@BeforeAll
	public static void setUp() {
		inventory = new BookInventory();
		inventory.books = MasterData.getBookList();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test()
	public void testBookIsbnAlreadyExists() throws IOException {
		Book book = MasterData.getBookData();
		book.setIsbn("1234567890");
		String errorMessage="";
		    try{
				inventory.addBook(book);
			}catch(ISBNAlreadyExistsException ex) {
				errorMessage = ex.getMessage();
			}
				
		
		String message = "The ISBN " + book.getIsbn() + " already exists";
		yakshaAssert(currentTest(), message.contentEquals(errorMessage) ? true : false, exceptionTestFile);

	}

}
