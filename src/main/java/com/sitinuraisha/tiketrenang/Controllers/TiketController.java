package com.sitinuraisha.tiketrenang.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sitinuraisha.tiketrenang.Models.Tiket;
import com.sitinuraisha.tiketrenang.Services.TiketService;

@RestController
@RequestMapping("/api/tiket-renang")
public class TiketController {
    @Autowired
    private TiketService tiketService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTiket() {
        List<Tiket> tiketList = tiketService.getAllTiket();
        Map<String, Object> response = new HashMap<>();
        response.put("data", tiketList);
        response.put("message", "Berhasil Menampilkan Data!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTiketById(@PathVariable Long id) {
        Tiket tiket = tiketService.getTiketById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));
        Map<String, Object> response = new HashMap<>();
        response.put("data", tiket);
        response.put("message", "Berhasil Menampilkan Data!");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTiket(@RequestBody Tiket tiket) {
        Tiket savedTiket = tiketService.createTiket(tiket);
        Map<String, Object> response = new HashMap<>();
        response.put("data", savedTiket);
        response.put("message", "Berhasil Menambahkan Data!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTiket(@PathVariable Long id, @RequestBody Tiket tiketDetails) {
        Tiket tiket = tiketService.getTiketById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));

        tiket.setCustomerName(tiketDetails.getCustomerName());
        tiket.setPurchaseDate(tiketDetails.getPurchaseDate());
        tiket.setPrice(tiketDetails.getPrice());

        Tiket updatedTiket = tiketService.createTiket(tiket);
        Map<String, Object> response = new HashMap<>();
        response.put("data", updatedTiket);
        response.put("message", "Berhasil Mengubah Data!");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTiket(@PathVariable Long id) {
        tiketService.deleteTiket(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Berhasil Menghapus Data!");
        return ResponseEntity.ok(response);
    }
}
