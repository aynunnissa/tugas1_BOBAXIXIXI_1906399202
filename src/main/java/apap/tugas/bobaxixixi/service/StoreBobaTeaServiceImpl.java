package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.StoreBobaTeaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreBobaTeaServiceImpl implements StoreBobaTeaService {

    @Autowired
    StoreBobaTeaDb storeBobaTeaDb;

    @Override
    public void addStoreBobaTea(StoreBobaTeaModel storeBoba) {
        storeBobaTeaDb.save(storeBoba);
    }

    @Override
    public Optional<StoreBobaTeaModel> getListProductionCode(String id) {
        return storeBobaTeaDb.findByProductionCode(id);
    }

    @Override
    public void deleteStoreBobaTeaModelByIdStore(StoreModel id) {
        storeBobaTeaDb.deleteStoreBobaTeaModelByIdStore(id);
    }

    @Override
    public void deleteStoreBobaTeaModelByIdBoba(BobaTeaModel id) {
        storeBobaTeaDb.deleteStoreBobaTeaModelByIdBoba(id);
    }

    @Override
    public List<StoreBobaTeaModel> getListBobaByIdStore(StoreModel id) {
        return storeBobaTeaDb.findByIdStore(id);
    }

    @Override
    public List<StoreBobaTeaModel> getListStoreByIdBoba(BobaTeaModel id) {
        return storeBobaTeaDb.findByIdBoba(id);
    }
}