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
		
		// precondition
		assert(Math.abs(gpsCoordinates[0]) <= 90 && Math.abs(gpsCoordinates[1]) <= 180);
		
		locationType = "GPS Coordinates";
		this.gpsCoordinates = gpsCoordinates;
		
		// postcondition
		assert(this.gpsCoordinates.equals(gpsCoordinates));
	}
	public GpsLocation(double lat, double lon)
	{
		
		// precondition
		assert(Math.abs(lat) <= 90 && Math.abs(lon) <= 180);
		
		locationType = "GPS Coordinates";
		gpsCoordinates = new double[]{lat, lon};
		
		// postcondition
		assert(gpsCoordinates[0] == lat && gpsCoordinates[1] == lon);
	}
	@Override
	public double[] getGpsCoordinates()
	{
		
		// precondition
		assert(gpsCoordinates != null);
		return gpsCoordinates;
	}
	@Override
	public String getMapcode()
	{
		
		// precondition
		assert(gpsCoordinates != null);
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
		
		// precondition
		assert(gpsCoordinates != null);
		this.gpsCoordinates = gpsCoordinates;
		
		// postcondition
		assert(gpsCoordinates.equals(gpsCoordinates));
	}
	@Override
	public String asString()
	{
		assert(gpsCoordinates != null);
		return gpsCoordinates[0] + " " + gpsCoordinates[1];
	}
}
