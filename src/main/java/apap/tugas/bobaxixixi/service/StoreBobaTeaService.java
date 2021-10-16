package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;

import java.util.List;
import java.util.Optional;

public interface StoreBobaTeaService {
    void addStoreBobaTea(StoreBobaTeaModel storeBoba);
    void deleteStoreBobaTeaModelByIdStore(StoreModel id);
    void deleteStoreBobaTeaModelByIdBoba(BobaTeaModel id);
    Optional<StoreBobaTeaModel> getListProductionCode(String id);
    List<StoreBobaTeaModel> getListBobaByIdStore(StoreModel id);
    List<StoreBobaTeaModel> getListStoreByIdBoba(BobaTeaModel id);
}
