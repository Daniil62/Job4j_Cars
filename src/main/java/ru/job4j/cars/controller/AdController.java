package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Account;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.utils.FilterParams;
import ru.job4j.cars.service.AdService;
import ru.job4j.cars.utils.QueryFilterMaster;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdController {

    private final AdService service;

    public AdController(AdService service) {
        this.service = service;
    }

    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public String listBooks(Model model, HttpSession session,
                            @ModelAttribute Optional<FilterParams> optionalParams,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        FilterParams params = optionalParams
                .orElse(new FilterParams(false, 0, 0, "", ""));
        if (page.isEmpty() || session.getAttribute("params") == null) {
            session.setAttribute("params", params);
        }
        addAccountAttribute(model, session);
        addFiltersAttribute(model, session);
        Page<Ad> adPage = setPage(session, page, size);
        model.addAttribute("ads", adPage);
        setTotalPages(adPage, model);
        return "mainPage";
    }

    private Page<Ad> setPage(HttpSession session, Optional<Integer> page, Optional<Integer> size) {
        return service
                .findPaginated(PageRequest.of(page.orElse(1) - 1,
                                size.orElse(5)),
                        service.findWithFilters(new
                                QueryFilterMaster((FilterParams)
                                session.getAttribute("params"))));
    }

    private void setTotalPages(Page<Ad> adPage, Model model) {
        int totalPages = adPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

    @GetMapping("/adPage/{adId}")
    public String adPage(Model model, HttpSession session,
                         @PathVariable("adId") long adId) {
        addAccountAttribute(model, session);
        Optional<Ad> optionalAd = service.findById(adId);
        model.addAttribute("ad", optionalAd.orElseGet(Ad::new));
        return "adPage";
    }

    @GetMapping("/myAdsPage")
    public String myAdsPage(Model model, HttpSession session) {
        addAccountAttribute(model, session);
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("ads", account != null
                ? service.findByUser(account.getId())
                : List.of());
        return "myAdsPage";
    }

    @GetMapping("/createAdForm")
    public String createAdForm(Model model, HttpSession session) {
        addAccountAttribute(model, session);
        loadDataToModel(model, new Ad());
        return "createAdForm";
    }

    @PostMapping("/createAd")
    public String createAd(@ModelAttribute Ad ad,
                           @RequestParam("file") MultipartFile file, Model model,
                           HttpSession session) throws IOException {
        addAccountAttribute(model, session);
        ad.setPhoto(file.getBytes());
        ad.setAccount((Account) session.getAttribute("account"));
        service.create(ad);
        return "redirect:/myAdsPage";
    }

    @GetMapping("/editAdForm/{adId}")
    public String updateAdForm(Model model, HttpSession session,
                               @PathVariable("adId") long id) {
        addAccountAttribute(model, session);
        Optional<Ad> optionalAd = service.findById(id);
        loadDataToModel(model, optionalAd.orElseGet(Ad::new));
        return "editAdForm";
    }

    @PostMapping("/updateAd")
    public String updateAd(@ModelAttribute Ad ad,
                           @RequestParam("file") MultipartFile file,
                           Model model,
                           HttpSession session) throws IOException {
        addAccountAttribute(model, session);
        byte[] photo = file.getBytes();
        ad.setPhoto(photo.length > 0 ? photo : service.getAdPhoto(ad.getId()));
        ad.setAccount((Account) session.getAttribute("account"));
        service.update(ad);
        return "redirect:/myAdsPage";
    }

    @PostMapping("/changeAdStatus/{adId}")
    public String removeAd(Model model, HttpSession session,
                           @PathVariable("adId") long adId) {
        addAccountAttribute(model, session);
        service.findById(adId).ifPresent(ad ->
                service.changeStatus(adId, !ad.isSold()));
        return "redirect:/myAdsPage";
    }

    @PostMapping("/deleteAd/{adId}")
    public String deleteAd(Model model, HttpSession session,
                           @PathVariable("adId") long adId) {
        addAccountAttribute(model, session);
        service.delete(adId);
        return "redirect:/myAdsPage";
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") long id) {
        Optional<Ad> optionalAd = service.findById(id);
        Ad ad = optionalAd.orElseGet(Ad::new);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(ad.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(ad.getPhoto()));
    }

    private void loadDataToModel(Model model, Ad ad) {
        model.addAttribute("ad", ad);
        model.addAttribute("enTypes", service.getEngineTypes());
        model.addAttribute("enVols", service.getEngineVolumes());
        model.addAttribute("trTypes", service.getTransmissionTypes());
        model.addAttribute("gears", service.getTransmissionGears());
        model.addAttribute("drives", service.getDrives());
        model.addAttribute("bodyTypes", service.getBodyTypes());
        model.addAttribute("colors", service.getBodyColors());
        model.addAttribute("owners", service.getOwnersCounts());
    }

    private void addAccountAttribute(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            account = new Account();
            account.setName("Гость");
        }
        model.addAttribute("account", account);
    }

    private void addFiltersAttribute(Model model, HttpSession session) {
        FilterParams params = (FilterParams) session.getAttribute("params");
        if (params == null) {
            params = new FilterParams(false, 0, 0, "", "");
        }
        model.addAttribute("params", params);
    }
}