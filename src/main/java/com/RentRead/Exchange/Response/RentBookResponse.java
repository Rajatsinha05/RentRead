package com.RentRead.Exchange.Response;

import com.RentRead.Enum.Role;
import com.RentRead.Models.BookStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentBookResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private List<BookStore> books;
}
