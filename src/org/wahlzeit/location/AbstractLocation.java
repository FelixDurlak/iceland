package org.wahlzeit.location;

public abstract class AbstractLocation implements Location
{
	protected String locationType;
	public abstract String getMapcode();
	public abstract double[] getGpsCoordinates();
	public abstract void readInMapcode(String mapcode);
	protected abstract void doReadInGpsCoordinates(double[] gpsCoordinates) throws GpsException;
	public abstract String asString();


	public String getLocationType(){
		return this.locationType;
	}

	public void readInGpsCoordinates(double[] gpsCoordinates) throws GpsException
	{
		doReadInGpsCoordinates(gpsCoordinates);
	}
	public void readInGpsCoordinates(double lat, double lon) throws GpsException
	{
		doReadInGpsCoordinates(new double[]{lat, lon});
	}
}
