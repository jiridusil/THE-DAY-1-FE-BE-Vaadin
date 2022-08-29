package web.parujeme.application.backend.service;

/**
 * @author jdusil
 * @date 2022-08-05 3:19 PM
 */

import org.springframework.stereotype.Service;
import web.parujeme.application.backend.entity.Company;
import web.parujeme.application.backend.entity.Contact;
import web.parujeme.application.backend.repository.CompanyRepository;
import web.parujeme.application.backend.repository.ContactRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class
            .getName());
    private ContactRepository contactRepository;
    private CompanyRepository companyRepository;
    public ContactService(ContactRepository contactRepository,
                          CompanyRepository
                                  companyRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
    public long count() {
        return contactRepository.count();
    }
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }
    public void save(Contact contact) {
        if (contact == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactRepository.save(contact);
    }

    public List<Contact> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }

    public String searchEmail(String email) {
        return contactRepository.searchEmail(email);
    }

    @PostConstruct
    public void populateTestData() {
        if (companyRepository.count() == 0) {
            companyRepository.saveAll(
                    Stream.of("Path-Way Electronics", "E-Tech Management", "Path-E-Tech Management")
                                    .map(Company::new)
                                    .collect(Collectors.toList()));
        }
        if (contactRepository.count() == 0) {
            Random r = new Random(0);
            List<Company> companies = companyRepository.findAll();
            contactRepository.saveAll(
                    Stream.of("Pavel Nezadany", "Jiri Volny")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Contact contact = new Contact();
                                contact.setFirstName(split[0]);
                                contact.setLastName(split[1]);
//                                contact.setCompany(companies.get(r.nextInt(companies.size())));
//                                contact.setStatus(Contact.Status.values()[r.nextInt(Contact
//                                        .Status.values().length)]);
                                String email = (contact.getFirstName() + "." + contact
                                        .getLastName() + "@gmail.com").toLowerCase();
                                contact.setEmail(email);
                                return contact;
                            }).collect(Collectors.toList()));
        }
    }
}
