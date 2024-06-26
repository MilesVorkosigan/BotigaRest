package com.Luis.gestioBotiga.bussiness.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="BOTIGUES")
public class Botiga implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	
	private String name;
	private String adress;
	private boolean open;
	@Enumerated(EnumType.STRING)
	private District district;
	@Enumerated(EnumType.STRING)
	private Sector sector;
	@Enumerated(EnumType.STRING)
	private Subsector subsector;
	private boolean franquicia;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public Subsector getSubsector() {
		return subsector;
	}
	public void setSubsector(Subsector subsector) {
		this.subsector = subsector;
	}
	public boolean isFranquicia() {
		return franquicia;
	}
	public void setFranquicia(boolean franquicia) {
		this.franquicia = franquicia;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		
		  final int prime = 31;
		    int result = 1;
		    result = prime * result + ((id == null) ? 0 : id.hashCode());
		    return result;
	}
	@Override
	public boolean equals(Object o) {
		  if (this == o)
		        return true;
		    if (o == null)
		        return false;
		    if (getClass() != o.getClass())
		        return false;
		    Botiga other = (Botiga) o;
		    if (id == null) {
		        if (other.id != null)
		            return false;
		    } else if (!id.equals(other.id))
		        return false;
		    return true;
		
	}
	@Override
	public String toString() {
		return "Botiga{" +
	            "id=" + id +
	            ", name='" + name + '\'' +
	            ", adress='" + adress + '\'' +
	            ", open=" + open +
	            ", district=" + district +
	            ", sector=" + sector +
	            ", subsector=" + subsector +
	            ", franquicia=" + franquicia +
	            '}';
	}
	
	

}
