package com.RentRead.Models;

import com.RentRead.Enum.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long  id;

    private String author;
    private String title;
    private String genre;
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus=AvailabilityStatus.AVAILABLE;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rental> rentals = new ArrayList<>();


}
