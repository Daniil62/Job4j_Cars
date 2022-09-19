package ru.job4j.cars.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.details.body.BodyColor;
import ru.job4j.cars.model.details.body.BodyType;
import ru.job4j.cars.model.details.engine.EngineType;
import ru.job4j.cars.model.details.transmission.TransmissionType;
import ru.job4j.cars.repository.AdRepository;
import ru.job4j.cars.repository.details.BodyRepository;
import ru.job4j.cars.repository.details.EngineRepository;
import ru.job4j.cars.repository.details.TransmissionRepository;
import ru.job4j.cars.utils.QueryFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AdService {

    private final AdRepository repository;
    private final BodyRepository bodyRepository;
    private final EngineRepository engineRepository;
    private final TransmissionRepository transmissionRepository;

    public AdService(AdRepository repository,
                     BodyRepository bodyRepository,
                     EngineRepository engineRepository,
                     TransmissionRepository transmissionRepository) {
        this.repository = repository;
        this.bodyRepository = bodyRepository;
        this.engineRepository = engineRepository;
        this.transmissionRepository = transmissionRepository;
    }

    public Ad create(Ad ad) {
        return repository.create(ad);
    }

    public List<Ad> findAll() {
        return repository.findAll();
    }

    public List<Ad> findAllActive() {
        return repository.findAllActive();
    }

    public List<Ad> findWithFilters(QueryFilter<Ad> filter) {
        return repository.findWithFilters(filter);
    }

    public Optional<Ad> findById(long id) {
        return repository.findById(id);
    }

    public List<Ad> findByUser(long userId) {
        return repository.findByUser(userId);
    }

    public void update(Ad newAd) {
        repository.update(newAd);
    }

    public boolean changeStatus(long id, boolean value) {
        return repository.changeStatus(id, value);
    }

    public boolean delete(long id) {
        return repository.delete(id);
    }

    public List<BodyType> getBodyTypes() {
        return bodyRepository.getTypes();
    }

    public List<BodyColor> getBodyColors() {
        return bodyRepository.getColors();
    }

    public List<EngineType> getEngineTypes() {
        return engineRepository.getTypes();
    }

    public List<Float> getEngineVolumes() {
        List<Float> result = new ArrayList<>();
        for (double i = 0.5; i < 8.0; i += 0.1) {
            result.add((float) i);
        }
        return result;
    }

    public List<TransmissionType> getTransmissionTypes() {
        return transmissionRepository.getTypes();
    }

    public List<Integer> getTransmissionGears() {
        return IntStream.range(1, 9).boxed().collect(Collectors.toList());
    }

    public List<String> getDrives() {
        return transmissionRepository.getDrives();
    }

    public List<Integer> getOwnersCounts() {
        return IntStream.range(1, 10).boxed().collect(Collectors.toList());
    }

    public byte[] getAdPhoto(long id) {
        return repository.getAdPhoto(id);
    }



    public Page<Ad> findPaginated(Pageable pageable, List<Ad> ads) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Ad> list;
        if (ads.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, ads.size());
            list = ads.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), ads.size());
    }
}
