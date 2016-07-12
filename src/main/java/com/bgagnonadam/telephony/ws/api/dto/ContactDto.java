package com.bgagnonadam.telephony.ws.api.dto;

/**
 * Created by Bruno on 2016-03-05.
 */
public class ContactDto {
  private String id;
  private String telephoneNumber;
  private String address;
  private String name;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "ContactDto{" +
            "id='" + id + '\'' +
            ", telephoneNumber='" + telephoneNumber + '\'' +
            ", address='" + address + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
