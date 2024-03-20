package com.bnp.cardiff.policies.services.impl;

import com.bnp.cardiff.policies.models.Covers;
import com.bnp.cardiff.policies.models.ProtectionPolicies;
import com.bnp.cardiff.policies.repository.PoliciesRepository;
import com.bnp.cardiff.policies.services.IService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoliciesServiceImpl implements IService<ProtectionPolicies> {

    private final PoliciesRepository policiesRepository;
    private  int counter = 0; // Valeur initiale

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Collection<ProtectionPolicies> findAll() {
        return policiesRepository.findAll();
    }

    @Override
    public Optional<ProtectionPolicies> findById(Integer id) {
        return policiesRepository.findById(id);
    }

    @Override
    public ProtectionPolicies saveOrUpdate(ProtectionPolicies protectionPolicies) {
        String id= generateCode();
        protectionPolicies.setIdPolicies(id);
        for(Covers covers:protectionPolicies.getCovers()){
            covers.setIdCovers(id);
        }
        return policiesRepository.save(protectionPolicies);
    }
    @Override
    public void deleteById(Integer id) {
        policiesRepository.deleteById(id);
    }

    public  String generateCode() {
        counter++;
        // Génération de l'ID du contrat au format "GT" suivi d'une suite de chiffres
        String contractId = "GT" + String.format("%08d", counter);
        Optional<ProtectionPolicies> protectionPolicies	= policiesRepository.findByIdPolicies(contractId);
        while (protectionPolicies.isPresent()) {
            counter++;
            contractId = "GT" + String.format("%08d", counter);
            protectionPolicies = policiesRepository.findByIdPolicies(contractId);
        }
        return contractId;
    }

    public List<ProtectionPolicies> getProtectionPolicies(String id, LocalDate date, boolean history) {
        return policiesRepository.findProtectionPolicies(id, date, history);
    }
    public ProtectionPolicies findProtectionPoliciesByContractId(String idPolicies){
        return policiesRepository.findByIdPolicies(idPolicies).get();
    }
}
