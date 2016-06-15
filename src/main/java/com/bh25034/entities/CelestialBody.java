package com.bh25034.entities;

public interface CelestialBody {

	public String getName();
	
	public double getGravity();
	
	public double getCircumference();
	
	public double getMass();
	
	public void setName(String name);
	
	public void setGravity(double gravity);
	
	public void setCircumference(double circumference);
	
	public void setMass(double mass);
	
}
