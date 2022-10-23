package rikkei.academy.service.province;

import org.springframework.beans.factory.annotation.Autowired;
import rikkei.academy.model.Province;
import rikkei.academy.repository.IProvinceRepository;

import java.util.Optional;

public class ProvinceServiceIMPL implements IProvinceService {
    @Autowired
    private IProvinceRepository providerRepository;

    @Override
    public Iterable<Province> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Optional<Province> findById(Long id) {
        return providerRepository.findById(id);
    }

    @Override
    public void save(Province province) {
        providerRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        providerRepository.deleteById(id);
    }
}
