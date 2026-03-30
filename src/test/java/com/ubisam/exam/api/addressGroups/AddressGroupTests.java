package com.ubisam.exam.api.addressGroups;

import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam.domain.AddressGroup;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressGroupTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private AddressGroupDocs docs;

  @Autowired
  private AddressGroupRepository addressGroupRepository;

  @Test
  void contextLoads() throws Exception{
    // Crud - C
    mvc.perform(post("/api/addressGroups").content(docs::newEntity, "회사 그룹"))
    .andDo(print()).andExpect(is2xx()).andDo(result(docs::context, "entity1"));

    // Crud - R
    String uri = docs.context("entity1", "$._links.self.href");
    mvc.perform(post(uri)).andExpect(is2xx());

    // Crud - U
    Map<String, Object> body = docs.context("entity1","$");
    mvc.perform(put(uri).content(docs::updateEntity, body, "친구 그룹")).andExpect(is2xx());

    // Crud - D
    mvc.perform(delete(uri)).andExpect(is2xx());
  }

  @Test
  void contextLoads2() throws Exception{
    List<AddressGroup> result;
    boolean hasResult;

    // 40개의 그룹 추가
    List<AddressGroup> addressGroupLists = new ArrayList<>();
    for(int i= 1; i <= 40; i++){
      addressGroupLists.add(docs.newEntity(i+"그룹", "그룹"+i+"입니다."));
    }
    addressGroupRepository.saveAll(addressGroupLists);

    JpaSpecificationBuilder<AddressGroup> nameQuery = JpaSpecificationBuilder.of(AddressGroup.class);
    nameQuery.where().and().eq("addressGroupName", "4그룹");
    result = addressGroupRepository.findAll(nameQuery.build());
    hasResult = result.stream().anyMatch(u -> "4그룹".equals(u.getAddressGroupName()));
    assertEquals(true, hasResult);

    JpaSpecificationBuilder<AddressGroup> descriptionQuery = JpaSpecificationBuilder.of(AddressGroup.class);
    descriptionQuery.where().and().eq("addressGroupDescription", "그룹5입니다.");
    result = addressGroupRepository.findAll(descriptionQuery.build());
    hasResult = result.stream().anyMatch(u -> "그룹5입니다.".equals(u.getAddressGroupDescription()));
    assertEquals(true, hasResult);
  }

  @Test
  void contextLoads3 () throws Exception{
    // 40개의 그룹 추가
    List<AddressGroup> addressGroupLists = new ArrayList<>();
    for(int i= 1; i <= 40; i++){
      addressGroupLists.add(docs.newEntity(i+"그룹", "그룹"+i+"입니다."));
    }
    addressGroupRepository.saveAll(addressGroupLists);

    String uri = "/api/addressGroups/search";
    // Search - 단일 검색
    mvc.perform(post(uri).content(docs::setSearchName, "6그룹")).andExpect(is2xx());
    mvc.perform(post(uri).content(docs::setSearchDescription, "그룹1입니다.")).andExpect(is2xx());

    // Search - 페이지네이션 5개씩 8페이지
    mvc.perform(post(uri).param("size", "5")).andExpect(is2xx());

    // Search - 정렬 addressGroupName,desc
    mvc.perform(post(uri).param("sort", "addressGroupName,desc")).andExpect(is2xx());
  }
  
}
