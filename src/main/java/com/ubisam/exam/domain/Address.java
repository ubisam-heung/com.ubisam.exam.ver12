package com.ubisam.exam.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "example_address")
public class Address {
  
  @Id
  @GeneratedValue
  private Long id;

  private String addressName;
  private String addressEmail;
  private String addressPhone;

  //  하나의 address가 여러 group에 속할 수 있음.
  //  예: 홍길동 → 회사 동료, 친구
  // @ManyToMany
  // private List<AddressGroup> addressGroups;

}
