package com.rmilab.dto;

import lombok.*;

import java.util.Map;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@ToString
//@Builder

public class Coordinates {
    private Center center;
    private Size size;
	public Coordinates() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coordinates(Center center, Size size) {
		super();
		this.center = center;
		this.size = size;
	}
	public Center getCenter() {
		return center;
	}
	public void setCenter(Center center) {
		this.center = center;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Coordinates [center=" + center + ", size=" + size + "]";
	}
    
    

}

