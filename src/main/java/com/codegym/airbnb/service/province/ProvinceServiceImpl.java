package com.codegym.airbnb.service.province;

import com.codegym.airbnb.model.Province;
import com.codegym.airbnb.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceServiceImpl implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepo;
    @Override
    public Iterable<Province> findAll() {
        return provinceRepo.findAll();
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepo.findById(id);
    }

    @Override
    public Province save(Province province) {
        return provinceRepo.save(province);
    }

    @Override
    public void delete(Long id) {
        provinceRepo.deleteById(id);
    }
}
