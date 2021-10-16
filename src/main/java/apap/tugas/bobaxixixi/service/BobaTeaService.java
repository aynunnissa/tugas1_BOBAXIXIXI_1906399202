package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;

import java.util.List;

public interface BobaTeaService {
	void addBobaTea(BobaTeaModel bobaTea);
    List<BobaTeaModel> getListBobaTea();
    BobaTeaModel getBobaTeaById(Long id);
    List<BobaTeaModel> listBobaTeaByName(String name);
    BobaTeaModel updateBobaTea(BobaTeaModel bobaTea);
    BobaTeaModel deleteBobaTea(BobaTeaModel bobaTea);
}