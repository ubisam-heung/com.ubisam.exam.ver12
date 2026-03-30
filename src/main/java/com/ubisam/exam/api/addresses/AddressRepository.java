package com.ubisam.exam.api.addresses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ubisam.exam.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address>{

  List<Address> findByAddressName(String addressName);
  List<Address> findByAddressEmail(String addressEmail);
  
}
