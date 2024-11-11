package com.casabella.backend.dto;

public interface SeasonalSupplementProjection {
    Long gteSeasonalSupplementId();
    Long getSeasonId();
    Double getPricePerUnit();
    String getSupplementName();
}
