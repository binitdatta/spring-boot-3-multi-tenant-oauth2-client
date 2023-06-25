package com.rollingstone.controller;

import com.rollingstone.config.CustomerClient;
import com.rollingstone.config.WelcomeClient;
import com.rollingstone.dto.CustomerDTO;
import com.rollingstone.model.Address;
import com.rollingstone.model.Customer;
import com.rollingstone.model.USState;
import com.rollingstone.model.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@SessionAttributes("authorizationRequest")
public class HomeController {

    private final CustomerClient customerClient;
    private final WelcomeClient welcomeClient;

    DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public HomeController(WelcomeClient welcomeClient, CustomerClient customerClient, WelcomeClient welcomeClient1) {
        this.customerClient = customerClient;
        this.welcomeClient = welcomeClient1;
    }
    Logger logger = LoggerFactory.getLogger("HomeController");
    @RequestMapping("/login")
    public String index(Model model){
            logger.info("Inside Login");
           return "login";
    }

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        String welcome = welcomeClient.sayHello();
        logger.info(" authentication.getName() : " + authentication.getName());
        logger.info("Welcome " + welcome);
        model.addAttribute("username", authentication.getName());

        return "logged_in";
    }

    @GetMapping("/customers")
    public String getEmployees(Authentication authentication, Model model) {
        List<Customer> customers = customerClient.getCustomers();
        logger.info("Size of the customer list : " + customers.size());
        model.addAttribute("username", authentication.getName());
        model.addAttribute("customerList", customers);
        return "customers";
    }

    @GetMapping("/addnew")
    public String addNewCustomer(Model model) {
        CustomerDTO customer = new CustomerDTO();
        Address address = new Address();
        model.addAttribute("customer", customer);
        //List<String> states = new ArrayList<String>();
        List<String> loyaltyStatusValues = new ArrayList<String>();
        loyaltyStatusValues.add("GOLD");
        loyaltyStatusValues.add("SILVER");
        loyaltyStatusValues.add("PLATINUM");
        loyaltyStatusValues.add("DIAMOND");

        List<USState> states = createStateList();
        model.addAttribute("states", states);
        model.addAttribute("loyaltyStatusValues", loyaltyStatusValues);
        return "new_customer";
    }
    // https://www.faa.gov/air_traffic/publications/atpubs/cnt_html/appendix_a.html
    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerDTO customer, BindingResult bindingResult, Model model) {
        logger.info(" Values" + customer.toString());


        if (customer.getCustomerNumber().startsWith("X"))
            bindingResult.addError(new FieldError("spaceship", "name", "We do not want " +
                    "Customer Number starting with an X!!!"));
        if (bindingResult.hasErrors()){
            List<String> loyaltyStatusValues = new ArrayList<String>();
            loyaltyStatusValues.add("GOLD");
            loyaltyStatusValues.add("SILVER");
            loyaltyStatusValues.add("PLATINUM");
            loyaltyStatusValues.add("DIAMOND");

            List<USState> states = createStateList();
            model.addAttribute("states", states);
            model.addAttribute("loyaltyStatusValues", loyaltyStatusValues);
            return "new_customer";
        }

        Customer customerEntity  = new Customer();
        logger.info("ID Customer ID!" + customer.getCustomerId());

        if (customer.getCustomerId() != 0) {
            customerEntity.setCustomerId(customer.getCustomerId());
        }
        customerEntity.setCustomerNumber(customer.getCustomerNumber());
        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setMiddleName(customer.getMiddleName());
        customerEntity.setLastName(customer.getLastName());

        LocalDate birthDateLd = LocalDate.parse(customer.getDateOfBirth(), DATEFORMATTER);
        LocalDateTime birthDateLdt = LocalDateTime.of(birthDateLd, LocalDateTime.now().toLocalTime());

        // customerEntity.setDateOfBirth(LocalDateTime.parse(customer.getDateOfBirth()));
        customerEntity.setDateOfBirth(birthDateLdt);

        LocalDate joinDateLd = LocalDate.parse(customer.getDateOfJoining(), DATEFORMATTER);
        LocalDateTime joinDateLdt = LocalDateTime.of(joinDateLd, LocalDateTime.now().toLocalTime());

        //customerEntity.setDateOfJoining(LocalDateTime.parse(customer.getDateOfJoining()));

        customerEntity.setDateOfJoining(joinDateLdt);

        customerEntity.setLoyaltyStatus(customer.getLoyaltyStatus());
        customerEntity.setLoyaltyPoints(customer.getLoyaltyPoints());

        User user = new User();

        user.setId(2);

        customerEntity.setUser(user);

        HashSet<Address> address = new HashSet<>();

        Address addressEntity = new Address();
        addressEntity.setHomePhoneNumber(customer.getHouseNumber());
        addressEntity.setStreet(customer.getStreet());
        addressEntity.setCity(customer.getCity());
        addressEntity.setState(customer.getState());
        addressEntity.setZip(customer.getZip());
        addressEntity.setHouseNumber(customer.getHouseNumber());
        addressEntity.setHomePhoneNumber(customer.getHomePhoneNumber());
        addressEntity.setMobilePhoneNumber(customer.getMobilePhoneNumber());
        addressEntity.setEmailAddress(customer.getEmailAddress());
        addressEntity.setCreatedBy("mhitter");
        addressEntity.setUpdatedBy("mhitter");
        address.add(addressEntity);
        customerEntity.setAddresses(address);
        customerEntity.setCreatedBy("mhitter");
        customerEntity.setUpdatedBy("mkhitter");
        try {
            if (customer.getCustomerId() != 0)
            {
                addressEntity.setAddressId(customer.getAddressId());
                customerClient.updateCustomer(customer.getCustomerId(), customerEntity);
            }else {
                customerClient.saveCustomer(customerEntity);
            }
        }
        catch(Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return "redirect:/customers";
    }

    @GetMapping("/security")
    public String security() {
        return "security";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        try {
            customerClient.deleteCustomer(id);
        }catch(Exception e) {
            logger.info(e.getLocalizedMessage());
        }
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String getCustomer(@PathVariable("id") long id, Model model) {
        logger.info("Received ID : " + id);

        Customer customer = customerClient.getCustomer(id);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setCustomerNumber(customer.getCustomerNumber());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setMiddleName(customer.getMiddleName());
        customerDTO.setLastName(customer.getLastName());
        logger.info("Address Size :" + customer.getAddresses().size());
        if (customer.getAddresses() != null && customer.getAddresses() .size() > 0) {
            customerDTO.setAddressId(customer.getAddresses().stream().findFirst().get().getAddressId());
            customerDTO.setCity(customer.getAddresses().stream().findFirst().get().getCity());
            customerDTO.setEmailAddress(customer.getAddresses().stream().findFirst().get().getEmailAddress());
            customerDTO.setHouseNumber(customer.getAddresses().stream().findFirst().get().getHouseNumber());
            customerDTO.setMobilePhoneNumber(customer.getAddresses().stream().findFirst().get().getMobilePhoneNumber());
            customerDTO.setHomePhoneNumber(customer.getAddresses().stream().findFirst().get().getHomePhoneNumber());
            customerDTO.setStreet(customer.getAddresses().stream().findFirst().get().getStreet());
            customerDTO.setState(customer.getAddresses().stream().findFirst().get().getState());
            customerDTO.setZip(customer.getAddresses().stream().findFirst().get().getZip());
        }

        customerDTO.setLoyaltyPoints(customer.getLoyaltyPoints());
        customerDTO.setLoyaltyStatus(customer.getLoyaltyStatus());
        customerDTO.setDateOfBirth(customer.getDateOfBirth().format(DATEFORMATTER));
        customerDTO.setDateOfJoining(customer.getDateOfJoining().format(DATEFORMATTER));


        List<String> loyaltyStatusValues = new ArrayList<String>();
        loyaltyStatusValues.add("GOLD");
        loyaltyStatusValues.add("SILVER");
        loyaltyStatusValues.add("PLATINUM");
        loyaltyStatusValues.add("DIAMOND");

        List<USState> states = createStateList();
        model.addAttribute("retrievedLoyaltyStatus", customerDTO.getLoyaltyStatus());
        model.addAttribute("existingCustomer", customerDTO);
        model.addAttribute("states", states);
        model.addAttribute("loyaltyStatusValues", loyaltyStatusValues);

        return "existing_customer";
    }

    @GetMapping("/header")
    public String header() {
        return "header";
    }

    @GetMapping("/oauth2")
    public String OAuth2() {
        return "oauth2-intro";
    }

    @GetMapping("/spring-security")
    public String SpringSecurity() {
        return "spring-security";
    }

    @GetMapping("/jwt")
    public String jwt() {
        return "jwt";
    }

    @GetMapping("/keycloak")
    public String keycloak() {
        return "keycloak";
    }

    @GetMapping("/oidc")
    public String oidc() {
        return "oidc";
    }
    @GetMapping("/asymmetric-encryption")
    public String asymmetricEncryption() {
        return "asymmetric-encryption";
    }

    @GetMapping("/keycloak-multi-idp")
    public String keyCloakMultiIdP() {
        return "keycloak-multi-idp";
    }

    @GetMapping("/owasp-top-ten")
    public String owaspTopTen() {
        return "owasp-top-ten";
    }

    @GetMapping("/spring-boot")
    public String SpringBoot() {
        return "spring-boot";
    }

    @GetMapping("/spring-di")
    public String SpringBootDI() {
        return "spring-di";
    }

    @GetMapping("/aop")
    public String aop() {
        return "aop";
    }

    @GetMapping("/elastic")
    public String elastic() {
        return "elastic";
    }

    @GetMapping("/prometheus")
    public String prometheus() {
        return "prometheus";
    }

    @GetMapping("/actuator-docs")
    public String actuator() {
        return "actuator-docs";
    }

    @GetMapping("/springsec-architecture")
    public String springSecurityArch() {
        return "springsec-architecture";
    }

    @GetMapping("/interface-driven-programming")
    public String interfaceDrivenProgramming() {
        return "interface-driven-programming";
    }

    @GetMapping("/spring-data-jpa")
    public String springDataJpa() {
        return "spring-data-jpa";
    }

    @GetMapping("/spring-event")
    public String springEvent() {
        return "spring-event";
    }

    private List<USState> createStateList(){

        List<USState> states = new ArrayList<>();

        USState Alabama = new USState("AL", "Alabama");
        states.add(Alabama);
        USState Alaska = new USState("AK", "Alaska");
        states.add(Alaska);
        USState Arizona = new USState("AZ", "Arizona");
        states.add(Arizona);
        USState Arkansas = new USState("AR", "Arkansas");
        states.add(Arkansas);
        USState AmericanSamoa = new USState("AS", "American Samoa");
        states.add(AmericanSamoa);

        USState California = new USState("CA", "California");
        states.add(California);

        USState Colorado = new USState("CO", "Colorado");
        states.add(Colorado);

        USState Connecticut = new USState("CT", "Connecticut");
        states.add(Connecticut);

        USState Delaware = new USState("DE", "Delaware");
        states.add(Delaware);
        USState DC = new USState("DC", "District of Columbia");
        states.add(DC);
        USState Florida = new USState("FL", "Florida");
        states.add(Florida);
        USState Georgia = new USState("GA", "Georgia");
        states.add(Georgia);
        USState Guam = new USState("GU", "Guam");
        states.add(Guam);
        USState Hawaii = new USState("HI", "Hawaii");
        states.add(Hawaii);
        USState Idaho = new USState("ID", "Idaho");
        states.add(Idaho);
        USState Illinois = new USState("IL", "Illinois");
        states.add(Illinois);
        USState Indiana = new USState("IN", "Indiana");
        states.add(Indiana);
        USState Iowa = new USState("IA", "Iowa");
        states.add(Iowa);
        USState Kansas = new USState("KS", "Kansas");
        states.add(Kansas);
        USState Kentucky = new USState("KY", "Kentucky");
        states.add(Kentucky);
        USState Louisiana = new USState("LA", "Louisiana");
        states.add(Louisiana);
        USState Maine = new USState("ME", "Maine");
        states.add(Maine);
        USState Maryland = new USState("MD", "Maryland");
        states.add(Maryland);
        USState Massachusetts = new USState("MD", "Massachusetts");
        states.add(Massachusetts);
        USState Michigan = new USState("MI", "Michigan");
        states.add(Michigan);
        USState Minnesota = new USState("MN", "Minnesota");
        states.add(Minnesota);
        USState Mississippi = new USState("MS", "Mississippi");
        states.add(Mississippi);
        USState Missouri = new USState("MO", "Missouri");
        states.add(Missouri);
        USState Montana = new USState("MT", "Montana");
        states.add(Montana);
        USState Nevada = new USState("NV", "Nevada");
        states.add(Nevada);
        USState NewHampshire = new USState("NH", "New Hampshire");
        states.add(NewHampshire);
        USState NewJersey = new USState("NJ", "New Jersey");
        states.add(NewJersey);
        USState NorthCarolina = new USState("NC", "North Carolina");
        states.add(NorthCarolina);
        USState NewMexico = new USState("NM", "New Mexico");
        states.add(NewMexico);

        USState NewYork = new USState("NY", "New York");
        states.add(NewYork);

        USState NorthDakota = new USState("ND", "North Dakota");
        states.add(NorthDakota);

        USState MarinaIslands = new USState("MP", "Northern Mariana Islands");
        states.add(MarinaIslands);

        USState Ohio = new USState("OH", "Ohio");
        states.add(Ohio);

        USState Oklahoma = new USState("OK", "Oklahoma");
        states.add(Oklahoma);

        USState Oregon = new USState("OR", "Oregon");
        states.add(Oregon);

        USState Pennsylvania = new USState("PA", "Pennsylvania");
        states.add(Pennsylvania);

        USState PuertoRico = new USState("PR", "Puerto Rico");
        states.add(PuertoRico);

        USState RhodeIsland = new USState("RI", "Rhode Island");
        states.add(RhodeIsland);

        USState SouthCarolina = new USState("SC", "South Carolina");
        states.add(SouthCarolina);

        USState SouthDakota = new USState("SD", "South Dakota");
        states.add(SouthDakota);

        USState Tennessee = new USState("TN", "Tennessee");
        states.add(Tennessee);

        USState Texas = new USState("TX", "Texas");
        states.add(Texas);

        USState Utah = new USState("UT", "Utah");
        states.add(Utah);

        return states;
    }
}
