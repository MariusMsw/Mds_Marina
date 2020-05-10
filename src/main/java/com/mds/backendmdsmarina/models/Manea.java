package com.mds.backendmdsmarina.models;

import com.mds.backendmdsmarina.utils.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Manea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String link;

    private String artist;
}
