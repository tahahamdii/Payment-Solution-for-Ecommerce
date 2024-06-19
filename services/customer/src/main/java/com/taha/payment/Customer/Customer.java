package com.taha.payment.Customer;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Customer {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
