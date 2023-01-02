package com.example.crisalisbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.Company;
import com.example.crisalisbackend.repository.CompanyRepository;

@Service
@Transactional
public class CompanyService {
    
    @Autowired
    CompanyRepository companyRepository;

    public List<Company> list(){
        return companyRepository.findAll();
    }

    public Optional<Company> getOne(int id){
        return companyRepository.findById(id);
    }

    public Optional<Company> getByCompanyName(String companyName){
        return companyRepository.findByCompanyName(companyName);
    }

    public void save(Company company){
        companyRepository.save(company);
    }

    public void delete(int id){
        companyRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return companyRepository.existsById(id);
    }
    
    public boolean existsByCuil(Integer cuil){
        return companyRepository.existsByCuil(cuil);
    } //prueba para validacion
}
