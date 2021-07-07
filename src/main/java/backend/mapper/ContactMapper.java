package backend.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import backend.domain.Contact;

@Mapper
public interface ContactMapper {
	
		void save(Contact contact);
		
		void delete(Long id);
		
		void update(Contact contact);
		
		Contact find(Long id);
		
		List<Contact> findAll();

		boolean existsById(Long id);
		
}
