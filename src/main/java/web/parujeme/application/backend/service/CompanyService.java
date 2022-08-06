package web.parujeme.application.backend.service;

/**
 * @author jdusil
 * @date 2022-08-05 3:19 PM
 */

import org.springframework.stereotype.Service;
import web.parujeme.application.backend.entity.Company;
import web.parujeme.application.backend.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}