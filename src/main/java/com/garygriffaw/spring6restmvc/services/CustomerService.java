package com.garygriffaw.spring6restmvc.services;

import com.garygriffaw.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> listCustomers();

    Optional<CustomerDTO> getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateById(UUID id, CustomerDTO customer);

    Boolean deleteById(UUID id);

    Optional<CustomerDTO> patchById(UUID id, CustomerDTO customer);
}
