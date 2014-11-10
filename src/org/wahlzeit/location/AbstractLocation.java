package org.wahlzeit.location;

public abstract class AbstractLocation implements Location
{
	protected String locationType;
	public abstract String getMapcode();
	public abstract double[] getGpsCoordinates();
	public abstract void readInMapcode(String mapcode);
	protected abstract void doReadInGpsCoordinates(double[] gpsCoordinates);
	public abstract String asString();
	
	
	public String getLocationType(){
		return this.locationType;
	}
	
	public void readInGpsCoordinates(double[] gpsCoordinates)
	{
		doReadInGpsCoordinates(gpsCoordinates);
	}
	public void readInGpsCoordinates(double lat, double lon)
	{
		doReadInGpsCoordinates(new double[]{lat, lon});
	}
}
