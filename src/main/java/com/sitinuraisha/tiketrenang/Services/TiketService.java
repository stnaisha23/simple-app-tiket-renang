package com.sitinuraisha.tiketrenang.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitinuraisha.tiketrenang.Models.Tiket;
import com.sitinuraisha.tiketrenang.Repositories.TiketRepository;

@Service
public class TiketService {
    @Autowired
    private TiketRepository tiketRepository;

    public List<Tiket> getAllTiket() {
        return tiketRepository.findAll();
    }

    public Optional<Tiket> getTiketById(Long id) {
        return tiketRepository.findById(id);
    }

    public Tiket createTiket(Tiket tiket) {
        return tiketRepository.save(tiket);
    }

    public void deleteTiket(Long id) {
        tiketRepository.deleteById(id);
    }
}
