package com.rmilab.dto;

import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class Center{
    private double x;
    private double y;
    private double z;
	public Center() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Center(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	@Override
	public String toString() {
		return "Center [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
    

}