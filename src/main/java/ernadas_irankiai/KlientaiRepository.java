package ernadas_irankiai;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KlientaiRepository extends CrudRepository<Klientai, Integer> {

	List<Klientai> findByKliento_pav(Integer kliento_pav);
	
	List<Klientai> findByFizinis_juridinis(Integer fizinis_juridinis);
	
}
