package com.example.springrabbitmqproducer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealerType {
	
	private long dealerTypeId;
	
	private String poiTypeCd;
	
	private String poiTypeName;
	
	private String poiTypeDescription;



	public long getDealerTypeId() {
		return dealerTypeId;
	}

	public void setDealerTypeId(long dealerTypeId) {
		this.dealerTypeId = dealerTypeId;
	}

	public String getPoiTypeCd() {
		return poiTypeCd;
	}

	public void setPoiTypeCd(String poiTypeCd) {
		this.poiTypeCd = poiTypeCd;
	}

	public String getPoiTypeName() {
		return poiTypeName;
	}

	public void setPoiTypeName(String poiTypeName) {
		this.poiTypeName = poiTypeName;
	}

	public String getPoiTypeDescription() {
		return poiTypeDescription;
	}

	public void setPoiTypeDescription(String poiTypeDescription) {
		this.poiTypeDescription = poiTypeDescription;
	}
	
	

}
