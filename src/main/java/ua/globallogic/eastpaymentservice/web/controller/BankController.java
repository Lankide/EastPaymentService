package ua.globallogic.eastpaymentservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.globallogic.eastpaymentservice.domain.Bank;
import ua.globallogic.eastpaymentservice.domain.Country;
import ua.globallogic.eastpaymentservice.service.BankService;

import java.beans.PropertyEditorSupport;
import java.util.Collection;

@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    @Qualifier("mvcBankService")
    BankService bankService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @PreAuthorize("hasPermission(null, 'add_bank')")
    public ModelAndView bankForm() {
        return new ModelAndView("bank/createBankForm").
                addObject("countries", bankService.getCountries()).
                addObject("bank", new Bank());
    }


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission(null, 'add_bank')")
    public ModelAndView create(Bank bank, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("bank/createBankForm").
                    addObject("globalMessage", "Ошибка при создании банка.").
                    addObject("alertCode", "FAIL");
        } else {
            bankService.save(bank);
            return new ModelAndView("bank/createBankForm").
                    addObject("globalMessage", "Новый банк успешно создан.").
                    addObject("alertCode", "OK");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission(null, 'view_bank_info')")
    public ModelAndView view(@PathVariable("id") int id) {
        return new ModelAndView("bank/bankInfo").addObject("bank", bankService.getBank(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission(null, 'view_bank_info')")
    public String list() {
        return "bank/viewAllBanks";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasPermission(null, 'view_bank_info')")
    public
    @ResponseBody
    ModelMap listJSON() {
        ModelMap modelMap = new ModelMap();
        Collection<Bank> banks = bankService.getBanks();
        modelMap.addAttribute("rows", banks.toArray(new Bank[banks.size()]));
        return modelMap;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Country.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String name) throws IllegalArgumentException {
                setValue(bankService.getCountryByName(name));
            }
        });
    }
}