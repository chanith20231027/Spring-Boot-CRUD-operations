package com.test.testing.controller;

import com.test.testing.dto.DonationDTO;
import com.test.testing.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    // Create a donation
    @PostMapping
    public DonationDTO createDonation(@RequestBody DonationDTO donationDTO) {
        return donationService.createDonation(donationDTO);
    }

    // Get all donations
    @GetMapping
    public List<DonationDTO> getAllDonations() {
        return donationService.getAllDonations();
    }

    // Get a donation by ID
    @GetMapping("/{id}")
    public DonationDTO getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    // Update a donation
    @PutMapping("/{id}")
    public DonationDTO updateDonation(@PathVariable Long id, @RequestBody DonationDTO donationDTO) {
        return donationService.updateDonation(id, donationDTO);
    }

    // Delete a donation
    @DeleteMapping("/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return "Donation deleted successfully";
    }
}
