package ua.globallogic.eastpaymentservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.globallogic.eastpaymentservice.domain.Company;
import ua.globallogic.eastpaymentservice.service.CompanyService;

import java.util.Collection;

@Controller
@RequestMapping ("/company")
public class CompanyController {

    @Autowired
    @Qualifier ("mvcCompanyService")
    CompanyService companyService;

    @RequestMapping (method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_INTERNAL_USER')")
    public String viewCompany (){
        return "company/viewAllCompanies";
    }

    @RequestMapping (value = "/all", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasRole('ROLE_INTERNAL_USER')")
    public @ResponseBody ModelMap listCompanies(){
        ModelMap model = new ModelMap();
        Collection<Company> companies = companyService.getAllCompanies();
        model.addAttribute("rows", companies.toArray(new Company[companies.size()]));
        return model;
    }
}
