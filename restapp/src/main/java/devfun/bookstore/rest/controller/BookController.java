package devfun.bookstore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import devfun.bookstore.common.domain.Book;
import devfun.bookstore.common.service.BookService;

@Controller
@RequestMapping(value = "/books")
public class BookController {
	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Book> getBooks() {
		List<Book> books = bookService.getBooks();
		return books;
	}

	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getBook(@PathVariable("id") Long id) {
		Book book = bookService.getBook(id);
		return String.format("결과 값은 %s 입니다", String.valueOf(book));
	}
	*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Book getBook(@PathVariable("id") Long id) {
	Book book = bookService.getBook(id);
	return book;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Book createBook(@RequestBody Book book) {
		bookService.createBook(book);
		Book selectedBook = bookService.getBook(book.getId());
		return selectedBook;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
		bookService.updateBook(book);
		Book selectedBook = bookService.getBook(book.getId());
		return selectedBook;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Book deleteBook(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		Book deletedBook = new Book();
		deletedBook.setId(id);
		return deletedBook;
	}
}