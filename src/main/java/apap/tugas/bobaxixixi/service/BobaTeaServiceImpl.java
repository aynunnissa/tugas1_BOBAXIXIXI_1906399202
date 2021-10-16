package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.repository.BobaTeaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BobaTeaServiceImpl implements BobaTeaService {

	@Autowired
    BobaTeaDb bobaTeaDb;

	@Override
    public List<BobaTeaModel> getListBobaTea(){
        return bobaTeaDb.findAllByOrderByNameAsc();
    }

    @Override
    public BobaTeaModel getBobaTeaById(Long id) {
        Optional<BobaTeaModel> bobaTea = bobaTeaDb.findById(id);
        if(bobaTea.isPresent()) return bobaTea.get();
        else return null;
    }

    @Override
    public List<BobaTeaModel> listBobaTeaByName(String name) {
        List<BobaTeaModel> bobaTea = bobaTeaDb.findByName(name);
        if(!bobaTea.isEmpty()) return bobaTea;
        else return null;
    }

    @Override
    public void addBobaTea(BobaTeaModel bobaTea) {
        bobaTeaDb.save(bobaTea);
    }

    @Override
    public BobaTeaModel updateBobaTea(BobaTeaModel bobaTea) {
        bobaTeaDb.save(bobaTea);
        return bobaTea;
    }

    @Override
    public BobaTeaModel deleteBobaTea(BobaTeaModel bobaTea) {
        bobaTeaDb.delete(bobaTea);
        return bobaTea;
    }
}