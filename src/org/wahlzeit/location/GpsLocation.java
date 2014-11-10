package org.wahlzeit.location;

import com.mapcode.MapcodeCodec;
import com.mapcode.Mapcode;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;
 

public class GpsLocation extends AbstractLocation
{
	private double[] gpsCoordinates;
	public GpsLocation()
	{
		locationType = "GPS Coordinates";
		gpsCoordinates = new double[]{0.0, 0.0};
	}
	public GpsLocation(double[] gpsCoordinates)
	{
		locationType = "GPS Coordinates";
		this.gpsCoordinates = gpsCoordinates;
	}
	public GpsLocation(double lat, double lon)
	{
		locationType = "GPS Coordinates";
		gpsCoordinates = new double[]{lat, lon};
	}
	@Override
	public double[] getGpsCoordinates()
	{
		return gpsCoordinates;
	}
	@Override
	public String getMapcode()
	{
		Mapcode mapcode = MapcodeCodec.encodeToInternational(gpsCoordinates[0], gpsCoordinates[1]);
		StringBuilder sb = new StringBuilder(mapcode.toString());
		sb.delete(0, 4);
		return sb.toString();
	}
	@Override
	public void readInMapcode(String mapcode)
	{
		Point point;
		try
		{
			point = MapcodeCodec.decode(mapcode);
			gpsCoordinates = new double[]{point.getLatDeg(), point.getLonDeg()};
		} catch(UnknownMapcodeException e)
		{
			e.printStackTrace();
		}
	}
	@Override
	protected void doReadInGpsCoordinates(double[] gpsCoordinates)
	{
		this.gpsCoordinates = gpsCoordinates;
	}
	@Override
	public String asString()
	{
		return gpsCoordinates[0] + " " + gpsCoordinates[1];
	}
}
