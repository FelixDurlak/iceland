package de.durlak;

public interface Location
{
	String getLocationType();
	String getMapcode();
	double[] getGpsCoordinates();
	void readInMapcode(String mapcode);
	void readInGpsCoordinates(double[] gpsCoordinates) throws GpsException;
	void readInGpsCoordinates(double lat, double lon) throws GpsException;
	String asString();
}