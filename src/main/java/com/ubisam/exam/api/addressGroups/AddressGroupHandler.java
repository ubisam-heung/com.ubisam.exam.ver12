package com.ubisam.exam.api.addressGroups;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.AddressGroup;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;
import io.u2ware.common.data.rest.core.annotation.HandleAfterRead;
import io.u2ware.common.data.rest.core.annotation.HandleBeforeRead;

@Component
@RepositoryEventHandler
public class AddressGroupHandler {

  @HandleBeforeRead
  public void beforeRead(AddressGroup e, Specification<AddressGroup> spec) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeRead] "+e);

    // 쿼리 작성
    JpaSpecificationBuilder<AddressGroup> query = JpaSpecificationBuilder.of(AddressGroup.class);
    query
      .where()
      .and().eq("addressGroupName", e.getSearchName())
      .and().eq("addressGroupDescription", e.getSearchDescription())
      .build(spec);
  }

  @HandleAfterRead
  public void afterRead(AddressGroup e, Serializable r) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterRead] "+e);
    System.out.println("[HandleafterRead] "+r);
  }

  @HandleBeforeCreate
  public void beforeCreate(AddressGroup e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeCreate] "+e);
  }

  @HandleBeforeSave
  public void beforeSave(AddressGroup e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeSave] "+e);
  }
  
  @HandleBeforeDelete
  public void beforeDelete(AddressGroup e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeDelete] "+e);
  }

  @HandleAfterCreate
  public void afterCreate(AddressGroup e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterCreate] "+e);
  }

  @HandleAfterSave
  public void afterSave(AddressGroup e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterSave] "+e);
  }
  
  @HandleAfterDelete
  public void afterDelete(AddressGroup e) throws Exception{
    // 로그 코드 작성 (Auditing)
    // 테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterDelete] "+e);
  }
}
