package org.wahlzeit.location;

public interface Location
{
	String getLocationType();
	String getMapcode();
	double[] getGpsCoordinates();
	void readInMapcode(String mapcode);
	void readInGpsCoordinates(double[] gpsCoordinates);
	void readInGpsCoordinates(double lat, double lon);
	String asString();
}