package com.rmilab.dto;

import java.util.Arrays;

import lombok.*;
/*
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Interaction {
    private String atom1;
    private int resNum1;
    private double[] coords1;
    private String atom2;
    private int resNum2;
    private double[] coords2;
    private double distance;
    String type;

}*/


import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class Interaction {
    // Interaction Participants
    private String atom1;
    private int resNum1;
    private String chainId1; // To identify the protein chain
    private String atom2;
    private int resNum2;
    private String chainId2;

    // Geometric Information
    private double[] coords1;
    private double[] coords2;
    private double distance;

    // Interaction Classification
    private InteractionType type;

    // Additional Data (Optional)
    private double angle; // For relevant interactions (hydrogen bonds)
    private boolean isBackboneInvolved; // Indicate if interaction happens with protein backbone
	public Interaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Interaction(String atom1, int resNum1, String chainId1, String atom2, int resNum2, String chainId2,
			double[] coords1, double[] coords2, double distance, InteractionType type, double angle,
			boolean isBackboneInvolved) {
		super();
		this.atom1 = atom1;
		this.resNum1 = resNum1;
		this.chainId1 = chainId1;
		this.atom2 = atom2;
		this.resNum2 = resNum2;
		this.chainId2 = chainId2;
		this.coords1 = coords1;
		this.coords2 = coords2;
		this.distance = distance;
		this.type = type;
		this.angle = angle;
		this.isBackboneInvolved = isBackboneInvolved;
	}
	public String getAtom1() {
		return atom1;
	}
	public void setAtom1(String atom1) {
		this.atom1 = atom1;
	}
	public int getResNum1() {
		return resNum1;
	}
	public void setResNum1(int resNum1) {
		this.resNum1 = resNum1;
	}
	public String getChainId1() {
		return chainId1;
	}
	public void setChainId1(String chainId1) {
		this.chainId1 = chainId1;
	}
	public String getAtom2() {
		return atom2;
	}
	public void setAtom2(String atom2) {
		this.atom2 = atom2;
	}
	public int getResNum2() {
		return resNum2;
	}
	public void setResNum2(int resNum2) {
		this.resNum2 = resNum2;
	}
	public String getChainId2() {
		return chainId2;
	}
	public void setChainId2(String chainId2) {
		this.chainId2 = chainId2;
	}
	public double[] getCoords1() {
		return coords1;
	}
	public void setCoords1(double[] coords1) {
		this.coords1 = coords1;
	}
	public double[] getCoords2() {
		return coords2;
	}
	public void setCoords2(double[] coords2) {
		this.coords2 = coords2;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public InteractionType getType() {
		return type;
	}
	public void setType(InteractionType type) {
		this.type = type;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public boolean isBackboneInvolved() {
		return isBackboneInvolved;
	}
	public void setBackboneInvolved(boolean isBackboneInvolved) {
		this.isBackboneInvolved = isBackboneInvolved;
	}
	@Override
	public String toString() {
		return "Interaction [atom1=" + atom1 + ", resNum1=" + resNum1 + ", chainId1=" + chainId1 + ", atom2=" + atom2
				+ ", resNum2=" + resNum2 + ", chainId2=" + chainId2 + ", coords1=" + Arrays.toString(coords1)
				+ ", coords2=" + Arrays.toString(coords2) + ", distance=" + distance + ", type=" + type + ", angle="
				+ angle + ", isBackboneInvolved=" + isBackboneInvolved + "]";
	}

    
}

