package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BobaTeaController {
	
	@Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("storeBobaTeaServiceImpl")
    @Autowired
    private StoreBobaTeaService storeBobaTeaService;

	@GetMapping("boba")
    public String listBobaTea(Model model) {
        //Mendapatkan semua BobaTeaModel
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();

        //Add variable semua BobaTeaModel ke "listBobaTea" untuk dirender pada thymeleaf
        model.addAttribute("listBobaTea", listBobaTea);

        //Return view template yang diinginkan
        return "viewall-boba-tea";
    };

    @GetMapping("boba/add")
    public String addBobaTeaFormPage( Model model ) {
        model.addAttribute("listTopping", toppingService.getListTopping());
        model.addAttribute("bobaTea", new BobaTeaModel());
        return "form-add-boba-tea";
    };

    @PostMapping("boba/add")
    public String addBobaTeaSubmitPage(
        @ModelAttribute BobaTeaModel bobaTea,
        Model model 
    ) {
        bobaTeaService.addBobaTea(bobaTea);
        model.addAttribute("id", bobaTea.getId());
        model.addAttribute("msg", bobaTea.getName() + " is successfully added!");
        return "add-boba-tea";
    };

    @GetMapping("/boba/update/{idBobaTea}")
    public String updateBobaTeaFormPage(
            @PathVariable Long idBobaTea,
            Model model
    ) {
        BobaTeaModel boba = bobaTeaService.getBobaTeaById(idBobaTea);
        model.addAttribute("listTopping", toppingService.getListTopping());
        model.addAttribute("boba", boba);
        return "form-update-boba-tea";
    }

    @PostMapping("/boba/update")
    public String updateBobaTeaSubmitPage(
            @ModelAttribute BobaTeaModel bobaTea,
            Model model
    ) {
        BobaTeaModel updatedBoba = bobaTeaService.updateBobaTea(bobaTea);

        model.addAttribute("id", updatedBoba.getId());
        model.addAttribute("msg", bobaTea.getName() + " is successfully updated!");
        return "add-boba-tea";
    }

    @GetMapping("/boba/delete/{idBobaTea}")
    public String deleteBobaTea (
            @PathVariable Long idBobaTea,
            Model model
    ) {
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaById(idBobaTea);

        model.addAttribute("id", idBobaTea);
        model.addAttribute("msg", bobaTea.getName() + " is successfully deleted!");
        storeBobaTeaService.deleteStoreBobaTeaModelByIdBoba(bobaTea);
        bobaTeaService.deleteBobaTea(bobaTea);
        return "add-boba-tea";
    }
}