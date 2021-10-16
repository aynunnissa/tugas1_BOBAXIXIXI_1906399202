package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.StoreService;
import apap.tugas.bobaxixixi.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

	@Qualifier("bobaTeaServiceImpl")
	@Autowired
	private BobaTeaService bobaTeaService;

	@Qualifier("toppingServiceImpl")
	@Autowired
	private ToppingService toppingService;

	@Qualifier("storeBobaTeaServiceImpl")
	@Autowired
	private StoreBobaTeaService storeBobaTeaService;

	@GetMapping("/")
	private String home() { return "home"; }

	@GetMapping("/search")
	public String bobaAvailability(
			@RequestParam(value = "bobaName", required = false) String bobaName,
			@RequestParam(value = "topping", required = false) String toppingName,
			Model model
	) {
		List<BobaTeaModel> listResult = new ArrayList<BobaTeaModel>();
		if (bobaName != null && toppingName != null) {
			List<BobaTeaModel> listBoba = bobaTeaService.listBobaTeaByName(bobaName);
			for (BobaTeaModel boba : listBoba) {
				if (toppingService.getToppingById(boba.getIdTopping().getId()).getName().equals(toppingName)) {
					listResult.add(boba);
				}
			}
		}
		model.addAttribute("listResult", listResult);
		model.addAttribute("listBoba", bobaTeaService.getListBobaTea());
		model.addAttribute("listTopping", toppingService.getListTopping());
		return "boba-availability";
	}

	@GetMapping("/filter/manager")
	public String filterManager(
		@RequestParam(value = "nameBoba", required = false) String nameBoba,
		Model model
	) {
		List<ManagerModel> listManager = new ArrayList<ManagerModel>();
		if (nameBoba != null) {
			List<BobaTeaModel> listBoba = bobaTeaService.listBobaTeaByName(nameBoba);
			for (BobaTeaModel boba : listBoba) {
				List<StoreBobaTeaModel> listStore = storeBobaTeaService.getListStoreByIdBoba(boba);
				for (StoreBobaTeaModel store : listStore) {
					if (store.getIdStore().getIdManager() != null) {
						listManager.add(store.getIdStore().getIdManager());
					}
				}
			}
		}
		model.addAttribute("listBoba", bobaTeaService.getListBobaTea());
		model.addAttribute("listManager", listManager);
		return "filter-manager";
	}
}