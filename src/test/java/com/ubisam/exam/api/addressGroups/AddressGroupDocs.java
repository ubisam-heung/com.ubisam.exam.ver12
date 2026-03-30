package com.ubisam.exam.api.addressGroups;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.AddressGroup;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class AddressGroupDocs extends MockMvcRestDocs{

    public AddressGroup newEntity(String... entity){
      AddressGroup body = new AddressGroup();
      body.setAddressGroupName(entity.length > 0 ? entity[0] : super.randomText("addressGroupName"));
      body.setAddressGroupDescription(entity.length > 1 ? entity[1] : super.randomText("addressGroupDescription"));
      return body;
    }

    public Map<String, Object> updateEntity(Map<String, Object> body, String entity){
      body.put("addressGroupName", entity);
      return body;
    }

    public Map<String, Object> setSearchName(String search){
      Map<String, Object> entity = new HashMap<>();
      entity.put("searchName", search);
      return entity;
    }

    public Map<String, Object> setSearchDescription(String search){
      Map<String, Object> entity = new HashMap<>();
      entity.put("searchDescription", search);
      return entity;
    }

}
  

