package com.example.spring03.models;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ContactDAO {

    private UUID id;
    private String name;
    private String email;


}
