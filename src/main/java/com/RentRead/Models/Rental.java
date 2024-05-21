package com.RentRead.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Rental {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long  id;
@ManyToOne
    private  User user;
@ManyToOne
private Book book;
}
