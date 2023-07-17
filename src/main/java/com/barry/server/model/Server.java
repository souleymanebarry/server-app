package com.barry.server.model;


import com.barry.server.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Server {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "IP Address cannot be empty or null")
    @NotBlank(message = "IP Address cannot be blank")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private Status status;
}
