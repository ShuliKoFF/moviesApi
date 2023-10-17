package ru.shrf.testjob.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscoverResponseDTO {
    private int page;

    @SerializedName("results")
    private List<MovieResponseDTO> results;
}
