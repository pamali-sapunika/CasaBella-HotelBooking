package com.casabella.backend.dto;

public interface SeasonalSupplementProjection {
    Long getSeasonalSupplementId();
    Long getSeasonId();
    Double getPricePerUnit();
    String getSupplementName();
}
