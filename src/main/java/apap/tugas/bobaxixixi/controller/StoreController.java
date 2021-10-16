package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.ManagerService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class StoreController {
	
	@Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;

    @Qualifier("storeBobaTeaServiceImpl")
    @Autowired
    private StoreBobaTeaService storeBobaTeaService;

	@GetMapping("store")
    public String listStore(Model model) {
        //Mendapatkan semua StoreModel
        List<StoreModel> listStore = storeService.getListStore();

        //Add variable semua StoreModel ke "listStore" untuk dirender pada thymeleaf
        model.addAttribute("listStore", listStore);

        //Return view template yang diinginkan
        return "viewall-store";
    };

    @GetMapping("/store/{idStore}")
    public String viewStore(
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreById(idStore);
        List<BobaTeaModel> listBoba = new ArrayList<BobaTeaModel>();
        for (StoreBobaTeaModel storeboba : storeBobaTeaService.getListBobaByIdStore(store)) {
            listBoba.add(bobaTeaService.getBobaTeaById(storeboba.getIdBoba().getId()));
        }
        model.addAttribute("store", store);
        model.addAttribute("listBoba", listBoba);
        return "view-store";
    }

    @GetMapping("store/add")
    public String addStoreFormPage( Model model ) {
        model.addAttribute("listManager", managerService.getListManager());
        model.addAttribute("store", new StoreModel());
        return "form-add-store";
    };

    @PostMapping("store/add")
    public String addStoreSubmitPage(
        @ModelAttribute StoreModel store,
        Model model 
    ) {
        List<String> huruf = List.of("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
        String random = "" + huruf.get((new Random()).nextInt(26)) + huruf.get((new Random()).nextInt(26));
        String reversed = store.getName().substring(2,3) + store.getName().substring(1,2) + store.getName().substring(0,1);
        String hours = store.getOpenHour().getHour() + "" + store.getCloseHour().getHour();
        int hhhh = Math.floorDiv(Integer.parseInt(hours), 10);
        String store_code = "SC" + reversed + String.format("%03d", hhhh) + random;
        while (storeService.getStoreByStoreCode(store_code) != null) {
           random = "" + huruf.get((new Random()).nextInt(26)) + huruf.get((new Random()).nextInt(26));
           store_code = "SC" + reversed + String.format("%03d", hhhh) + random;
        }
        store.setStoreCode(store_code.toUpperCase());
        storeService.addStore(store);
        model.addAttribute("store", store);
        model.addAttribute("msg", store.getName() + " with store code " + store.getStoreCode() + " successfully added!");
        return "add-store";
    };

    @GetMapping("/store/update/{idStore}")
    public String updateStoreFormPage(
            @PathVariable Long idStore,
            Model model
    ) {
        //Mendapatkan Store yang ingin diupdate sesuai dengan idStore
        StoreModel store = storeService.getStoreById(idStore);
        model.addAttribute("listManager", managerService.getListManager());
        model.addAttribute("store", store);
        return "form-update-store";
    }

    @PostMapping("/store/update")
    public String updateStoreSubmitPage(
            @ModelAttribute StoreModel store,
            Model model
    ) {
        //Mendapatkan Store yang ingin diupdate sesuai dengan idStore
        List<String> huruf = List.of("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
        String random = "" + huruf.get((new Random()).nextInt(26)) + huruf.get((new Random()).nextInt(26));
        String reversed = store.getName().substring(2,3) + store.getName().substring(1,2) + store.getName().substring(0,1);
        String hours = store.getOpenHour().getHour() + "" + store.getCloseHour().getHour();
        int hhhh = Math.floorDiv(Integer.parseInt(hours), 10);
        String store_code = "SC" + reversed + String.format("%03d", hhhh) + random;
        while (storeService.getStoreByStoreCode(store_code) != null) {
            random = "" + huruf.get((new Random()).nextInt(26)) + huruf.get((new Random()).nextInt(26));
            store_code = "SC" + reversed + String.format("%03d", hhhh) + random;
        }
        store.setStoreCode(store_code.toUpperCase());
        StoreModel updatedStore = storeService.updateStore(store);
        model.addAttribute("id", updatedStore.getId());
        model.addAttribute("msg", store.getName() + " with store code " + store.getStoreCode() + " successfully updated!");
        return "add-store";
    }

    @GetMapping("/store/delete/{idStore}")
    public String deleteStore (
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreById(idStore);

        model.addAttribute("id", idStore);
        model.addAttribute("msg", store.getName() + " with store code " + store.getStoreCode() + " is successfully deleted!");
        storeBobaTeaService.deleteStoreBobaTeaModelByIdStore(store);
        storeService.deleteStore(store);
        return "add-store";
    }
}