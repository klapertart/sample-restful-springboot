package com.ostrue.app.restfulsample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tritr
 * @since 6/21/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PesertaDto {
    @JsonProperty("nama")
    private String nama;
    @JsonProperty("email")
    private String email;
}
