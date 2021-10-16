package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreBobaTeaController {
    @Qualifier("storeBobaTeaServiceImpl")
    @Autowired
    private StoreBobaTeaService storeBobaTeaService;

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @GetMapping("/store/{idStore}/assign-boba")
    public String viewBoba(
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreById(idStore);
        List<StoreBobaTeaModel> selectedBobaTeaObj = storeBobaTeaService.getListBobaByIdStore(store);
        List<Long> selectedBobaTea = new ArrayList<Long>();
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        for (StoreBobaTeaModel obj : selectedBobaTeaObj) {
            selectedBobaTea.add(obj.getIdBoba().getId());
        }
        model.addAttribute("namaStore", store.getName());
        model.addAttribute("idStore", store.getId());
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("selectedBobaTea", selectedBobaTea);
        return "view-boba-temp";
    }

    @RequestMapping(value="/store/assign-boba", method=RequestMethod.POST)
    public String viewBoba(
            @RequestParam(value = "listBoba") List<BobaTeaModel> listBoba,
            @RequestParam(value = "idStore") String idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreById(Long.parseLong(idStore));
        storeBobaTeaService.deleteStoreBobaTeaModelByIdStore(store);
        for( BobaTeaModel boba : listBoba ) {
            StoreBobaTeaModel newRelation = new StoreBobaTeaModel();
            List<StoreBobaTeaModel> selectedBobaTeaObj = storeBobaTeaService.getListBobaByIdStore(store);
            newRelation.setIdBoba(boba);
            newRelation.setIdStore(store);
            String proCode = "FFFF" + boba.getId();
            newRelation.setProductionCode(proCode);
            if (storeBobaTeaService.getListProductionCode(proCode).isEmpty()) {
                storeBobaTeaService.addStoreBobaTea(newRelation);
            }
        }
        model.addAttribute("namaStore", store.getName());
        model.addAttribute("idStore", idStore);
        model.addAttribute("listBobaTea", listBoba);

        return "view-boba-test";
    }


    @GetMapping("/boba/{idBoba}/assign-store")
    public String viewStore(
            @PathVariable Long idBoba,
            Model model
    ) {
        BobaTeaModel boba = bobaTeaService.getBobaTeaById(idBoba);
        List<StoreBobaTeaModel> selectedStoreObj = storeBobaTeaService.getListStoreByIdBoba(boba);
        List<Long> selectedStore = new ArrayList<Long>();
        List<StoreModel> listStore = storeService.getListStore();
        for (StoreBobaTeaModel obj : selectedStoreObj) {
            selectedStore.add(obj.getIdStore().getId());
        }
        model.addAttribute("namaBoba", boba.getName());
        model.addAttribute("idBoba", boba.getId());
        model.addAttribute("listStore", listStore);
        model.addAttribute("selectedStore", selectedStore);
        return "view-store-temp";
    }

    @RequestMapping(value="/boba/assign-store", method=RequestMethod.POST)
    public String viewStore(
            @RequestParam(value = "listStore") List<StoreModel> listStore,
            @RequestParam(value = "idBoba") String idBoba,
            Model model
    ) {
        BobaTeaModel boba = bobaTeaService.getBobaTeaById(Long.parseLong(idBoba));
        storeBobaTeaService.deleteStoreBobaTeaModelByIdBoba(boba);
        for( StoreModel store : listStore ) {
            StoreBobaTeaModel newRelation = new StoreBobaTeaModel();
            newRelation.setIdBoba(boba);
            newRelation.setIdStore(store);
            int adaToping = 0;
            if (boba.getIdTopping() != null) {
                adaToping = 1;
            }
            String proCode = "PC" + String.format("%03d", store.getId()) + adaToping + String.format("%03d", boba.getId());
            newRelation.setProductionCode(proCode);
            storeBobaTeaService.addStoreBobaTea(newRelation);
        }
        model.addAttribute("namaBoba", boba.getName());
        model.addAttribute("idBoba", idBoba);
        model.addAttribute("listStore", listStore);

        return "view-store-test";
    }
}
