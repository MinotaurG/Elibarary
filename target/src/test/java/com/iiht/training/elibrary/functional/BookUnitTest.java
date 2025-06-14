package com.iiht.training.elibrary.functional;

import static com.iiht.training.elibrary.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.elibrary.testutils.TestUtils.businessTestFile;
import static com.iiht.training.elibrary.testutils.TestUtils.currentTest;
import static com.iiht.training.elibrary.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.elibrary.testutils.TestUtils.testReport;
import static com.iiht.training.elibrary.testutils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.iiht.training.elibrary.exception.ISBNAlreadyExistsException;
import com.iiht.training.elibrary.inventory.BookInventory;
import com.iiht.training.elibrary.model.Book;
import com.iiht.training.elibrary.testutils.MasterData;

class BookUnitTest {

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

	@Test
	public void testSaveBook() throws IOException {
		Book book = MasterData.getBookData();
        book.setIsbn("3212345670");
		yakshaAssert(currentTest(), inventory.addBook(book) ? true : false, businessTestFile);
	}

	@Test
	public void testGetAllBooks() throws IOException {
		List<Book> books = MasterData.getBookList();

		yakshaAssert(currentTest(), books.size() == 2 ? true : false, businessTestFile);
	}
}
