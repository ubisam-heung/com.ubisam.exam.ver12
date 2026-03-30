package com.ubisam.exam.api.addresses;


import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Address;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class AddressDocs extends MockMvcRestDocs{

  public Address newEntity(String... entity){
    Address body = new Address();
    body.setAddressName(entity.length > 0 ? entity[0] : super.randomText("addressName"));
    body.setAddressEmail(entity.length > 1 ? entity[1] : super.randomText("addressEmail"));
    body.setAddressPhone(super.randomText("addressPhone"));
    return body;
  }

  public Map<String, Object> updateEntity(Map<String, Object> body, String entity){
    body.put("addressName", entity);
    return body;
  }
  
}
