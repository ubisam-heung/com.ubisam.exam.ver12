package com.ubisam.exam.api.addresses;

import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.get;
import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam.domain.Address;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private AddressDocs docs;

  @Autowired
  private AddressRepository addressRepository;

  @Test
  void contextLoads () throws Exception{
    // Crud - C
    mvc.perform(post("/api/addresses").content(docs::newEntity, "홍길동")).andDo(print()).andExpect(is2xx()).andDo(result(docs::context, "entity1"));

    // Crud - R
    String uri = docs.context("entity1", "$._links.self.href");
    mvc.perform(get(uri)).andExpect(is2xx());

    // Crud - U
    Map<String, Object> body = docs.context("entity1", "$");
    mvc.perform(put(uri).content(docs::updateEntity, body, "김길동")).andExpect(is2xx());

    // Crud - D
    mvc.perform(delete(uri)).andExpect(is2xx());
  }

  @Test
  void contextLoads2() throws Exception{
    Specification<Address> spec;
    List<Address> result;
    boolean hasResult;

    // 40개의 주소록 추가
    List<Address> addressLists = new ArrayList<>();
    for(int i = 1; i <=40; i++){
      addressLists.add(docs.newEntity("길동"+i, "abc"+i+"@abc.com"));
    }
    addressRepository.saveAll(addressLists);

    JpaSpecificationBuilder<Address> nameQuery = JpaSpecificationBuilder.of(Address.class);
    spec = nameQuery.where().and().eq("addressName", "길동4").build();
    result = addressRepository.findAll(spec);
    hasResult = result.stream().anyMatch(u -> "길동4".equals(u.getAddressName()));
    assertEquals(true, hasResult);

    JpaSpecificationBuilder<Address> emailQuery = JpaSpecificationBuilder.of(Address.class);
    spec = emailQuery.where().and().eq("addressEmail", "abc4@abc.com").build();
    result = addressRepository.findAll(spec);
    hasResult = result.stream().anyMatch(u -> "abc4@abc.com".equals(u.getAddressEmail()));
    assertEquals(true, hasResult);
  }

  @Test
  void contextLoads3 () throws Exception{
    // 40개의 주소록 추가
    List<Address> addressLists = new ArrayList<>();
    for(int i = 1; i <=40; i++){
      addressLists.add(docs.newEntity("길동"+i, "abc"+i+"@abc.com"));
    }
    addressRepository.saveAll(addressLists);

    // Search - 단일 검색
    mvc.perform(get("/api/addresses/search/findByAddressName").param("addressName", "길동5")).andExpect(is2xx());
    mvc.perform(get("/api/addresses/search/findByAddressEmail").param("addressEmail", "abc3@abc.com")).andExpect(is2xx());

    // Search - 페이지네이션 8개씩 5페이지
    mvc.perform(get("/api/addresses").param("size", "8")).andExpect(is2xx());

    // Search - addressEmail,desc
    mvc.perform(get("/api/addresses").param("sort", "addressEmail,desc")).andExpect(is2xx());
  }
  
}
