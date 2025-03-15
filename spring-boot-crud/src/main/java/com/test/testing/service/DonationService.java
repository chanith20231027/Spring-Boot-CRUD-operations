package com.test.testing.service;

import com.test.testing.dto.DonationDTO;
import com.test.testing.model.Donation;
import com.test.testing.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    // Create a new donation
    public DonationDTO createDonation(DonationDTO donationDTO) {
        Donation donation = new Donation(null, donationDTO.getName(), donationDTO.getDonation());
        Donation savedDonation = donationRepository.save(donation);
        return new DonationDTO(savedDonation.getName(), savedDonation.getDonation());
    }

    // Get all donations
    public List<DonationDTO> getAllDonations() {
        return donationRepository.findAll()
                .stream()
                .map(donation -> new DonationDTO(donation.getName(), donation.getDonation()))
                .collect(Collectors.toList());
    }

    // Get donation by ID
    public DonationDTO getDonationById(Long id) {
        return donationRepository.findById(id)
                .map(donation -> new DonationDTO(donation.getName(), donation.getDonation()))
                .orElseThrow(() -> new RuntimeException("Donation not found"));
    }

    // Update a donation
    public DonationDTO updateDonation(Long id, DonationDTO donationDTO) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        donation.setName(donationDTO.getName());
        donation.setDonation(donationDTO.getDonation());

        Donation updatedDonation = donationRepository.save(donation);
        return new DonationDTO(updatedDonation.getName(), updatedDonation.getDonation());
    }

    // Delete a donation
    public void deleteDonation(Long id) {
        if (!donationRepository.existsById(id)) {
            throw new RuntimeException("Donation not found");
        }
        donationRepository.deleteById(id);
    }
}
