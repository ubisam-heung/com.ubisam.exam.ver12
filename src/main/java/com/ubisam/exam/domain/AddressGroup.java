package com.ubisam.exam.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "example_address_group")
public class AddressGroup {

  @Id
  @GeneratedValue
  private Long id;

  private String addressGroupName;

  private String addressGroupDescription;


  //  하나의 group에 여러 address가 포함될 수 있고,
  //  하나의 address도 여러 group에 속할 수 있음.
  //  예: '회사 동료' group에는 홍길동, 김길동 등이 포함될 수 있고,
  //  홍길동은 '회사 동료', '친구' group에 모두 속할 수 있음.
  // @ManyToMany
  // private List<Address> addresses;

  @Transient
  private String searchName;

  @Transient
  private String searchDescription;
  
}
