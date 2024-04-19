package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.CustomerAddressInfo;
import ge.ibsu.demo.dto.RequestData;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.services.CustomerService;
import ge.ibsu.demo.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET,produces = {"application/json"})
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {"application/json"})
    public Customer getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Customer add(@Validated @RequestBody AddCustomer addCustomer) throws Exception {
       GeneralUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "address"));

        return customerService.addEditCustomer(addCustomer, null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public Boolean delete(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer edit(@Validated @RequestBody AddCustomer addCustomer, @PathVariable Long id) throws Exception {
        GeneralUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "address"));
        return customerService.addEditCustomer(addCustomer, id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public List<Customer> search(@RequestBody SearchCustomer searchCustomer) throws Exception {
        GeneralUtil.checkRequiredProperties(searchCustomer, Arrays.asList("active", "searchText"));
        return customerService.search(searchCustomer);
    }
    @RequestMapping(value = "/searchAddress", method = RequestMethod.POST, produces = {"application/json"})
    public Page<CustomerAddressInfo> searchAddressInfo(@RequestBody RequestData<SearchCustomer> rd) throws Exception {
        GeneralUtil.checkRequiredProperties(rd.getData(), Arrays.asList("active", "searchText"));
        return customerService.searchAddressInfo(rd.getData(), rd.getPaging());
    }
}