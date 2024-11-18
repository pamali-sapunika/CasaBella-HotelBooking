package com.casabella.backend.dto;

public class SeasonalSupplementDTO {

    private Long seasonalSupplementId;
    private Long seasonId;
    private Double pricePerUnit;
    private String supplementName;


    //Getters and Setters
    public Long getSeasonId() {
        return seasonId;
    }
    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }
    public Double getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public String getSupplementName() {
        return supplementName;
    }
    public void setSupplementName(String supplementName) {
        this.supplementName = supplementName;
    }
    public Long getSeasonalSupplementId() {
        return seasonalSupplementId;
    }
    public void setSeasonalSupplementId(Long seasonalSupplementId) {
        this.seasonalSupplementId = seasonalSupplementId;
    }
    public SeasonalSupplementDTO(Long seasonalSupplementId, Long seasonId, Double pricePerUnit, String supplementName) {
        this.seasonalSupplementId = seasonalSupplementId;
        this.seasonId = seasonId;
        this.pricePerUnit = pricePerUnit;
        this.supplementName = supplementName;
    }
    public SeasonalSupplementDTO() {
    }
    
    
    

    

    
    

    


    
    
    
}
