package com.iiht.training.elibrary.model;

import java.util.Objects;

public class Book {

	private Integer id;
	private String isbn;
	private String name;
	private Boolean issued;
	private String publication;
	private String stream;

	public Book() {

	}

	public Book(Integer id, String isbn, String name, Boolean issued, String publication, String stream) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.issued = issued;
		this.publication = publication;
		this.stream = stream;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIssued() {
		return issued;
	}

	public void setIssued(Boolean issued) {
		this.issued = issued;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stream, id, isbn, name, publication, issued);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(stream, other.stream) && Objects.equals(id, other.id) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(name, other.name) && Objects.equals(publication, other.publication)
				&& Objects.equals(issued, other.issued);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", name=" + name + ", issued=" + issued + ", publication="
				+ publication + ", category=" + stream + "]";
	}

}
