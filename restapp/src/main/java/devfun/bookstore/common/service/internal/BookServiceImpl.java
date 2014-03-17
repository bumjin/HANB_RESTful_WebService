package devfun.bookstore.common.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import devfun.bookstore.common.domain.Book;
import devfun.bookstore.common.mapper.BookMapper;
import devfun.bookstore.common.service.BookService;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
	@Autowired BookMapper bookMapper;
	public List<Book> getBooks() {
		return bookMapper.select();
	}
	public Book getBook(Long id) {
		return bookMapper.selectByPrimaryKey(id);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int createBook(Book book) {
		return bookMapper.insert(book);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateBook(Book book) {
		return bookMapper.updateByPrimaryKey(book);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int deleteBook(Long id) {
		return bookMapper.deleteByPrimaryKey(id);
	}
}
