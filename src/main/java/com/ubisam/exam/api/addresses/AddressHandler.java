package com.ubisam.exam.api.addresses;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Address;

@Component
@RepositoryEventHandler
public class AddressHandler {

  @HandleBeforeCreate
  public void beforeCreate(Address e)throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeCreate] "+e);
  }
  @HandleBeforeSave
  public void beforeSave(Address e) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeSave] "+e);
  }
  
  @HandleBeforeDelete
  public void beforeDelete(Address e) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandlebeforeDelete] "+e);
  }

  @HandleAfterCreate
  public void afterCreate(Address e) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterCreate] "+e);
  }

  @HandleAfterSave
  public void afterSave(Address e) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterSave] "+e);
  }
  
  @HandleAfterDelete
  public void afterDelete(Address e) throws Exception{
    //로그 코드 작성 (Auditing)
    //테스트에서는 sysout으로 작성하나 실제는 log 사용
    System.out.println("[HandleafterDelete] "+e);
  }
}
