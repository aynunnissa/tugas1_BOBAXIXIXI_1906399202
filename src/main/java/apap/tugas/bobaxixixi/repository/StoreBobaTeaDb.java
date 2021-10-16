package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreBobaTeaDb extends JpaRepository<StoreBobaTeaModel, Long> {

    Optional<StoreBobaTeaModel> findByProductionCode(String id);
    List<StoreBobaTeaModel> findByIdStore(StoreModel id);
    List<StoreBobaTeaModel> findByIdBoba(BobaTeaModel id);

    void deleteStoreBobaTeaModelByIdStore(StoreModel id);
    void deleteStoreBobaTeaModelByIdBoba(BobaTeaModel id);
}
