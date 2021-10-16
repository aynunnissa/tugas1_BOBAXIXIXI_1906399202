package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.StoreDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

	@Autowired
    StoreDb storeDb;

	@Override
    public List<StoreModel> getListStore(){
        return storeDb.findAllByOrderByNameAsc();
    }

    @Override
    public void addStore(StoreModel store) {
        storeDb.save(store);
    }

    @Override
    public StoreModel getStoreByStoreCode(String code) {
        Optional<StoreModel> store = storeDb.findByStoreCode(code);
        if(store.isPresent()) return store.get();
        else return null;
    }

    @Override
    public StoreModel getStoreById(Long id) {
        Optional<StoreModel> store = storeDb.findById(id);
        if(store.isPresent()) return store.get();
        else return null;
    }

    @Override
    public StoreModel updateStore(StoreModel store) {
        storeDb.save(store);
        return store;
    }

    @Override
    public StoreModel deleteStore(StoreModel store) {
        storeDb.delete(store);
        return store;
    }
}