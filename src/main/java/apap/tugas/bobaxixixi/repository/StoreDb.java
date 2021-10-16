package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.StoreModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreDb extends JpaRepository<StoreModel, Long> {
	Optional<StoreModel> findById(Long id);
	Optional<StoreModel> findByStoreCode(String code);
	List<StoreModel> findAllByOrderByNameAsc();
}
