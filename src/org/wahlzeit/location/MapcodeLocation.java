package org.wahlzeit.location;

import com.mapcode.MapcodeCodec;
import com.mapcode.Mapcode;
import com.mapcode.Point;
import com.mapcode.UnknownMapcodeException;


public class MapcodeLocation extends AbstractLocation
{
	private String mapcode;
	public MapcodeLocation()
	{
		locationType = "Mapcode";
		mapcode = "";
	}
	public MapcodeLocation(String mapcode)
	{
		
		// precondition
		assert(mapcode != null);
		
		locationType = "Mapcode";
		this.mapcode = mapcode;
		
		// postcondition
		assert(this.mapcode.equals(mapcode));
	}
	@Override
	public double[] getGpsCoordinates()
	{
		Point point;
		double[] gpsCoordinates = new double[2];

		try {
			point = MapcodeCodec.decode(mapcode);
			gpsCoordinates = new double[]{point.getLatDeg(), point.getLonDeg()};
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnknownMapcodeException e) {
			e.printStackTrace();
		}

		return gpsCoordinates;
	}
	@Override
	public String getMapcode()
	{
		
		// precondition
		assert(mapcode != null);
		
		return mapcode;
	}
	@Override
	public void readInMapcode(String mapcode)
	{
		
		// precondition
		assert(mapcode != null);
		
		this.mapcode = mapcode;
	}
	@Override
	protected void doReadInGpsCoordinates(double[] gpsCoordinates) throws GpsException
	{
		
		// precondition
		if(Math.abs(gpsCoordinates[0]) > 90){
			throw new GpsException("latitude is not in the range from -90 to 90");
		}
		if(Math.abs(gpsCoordinates[1]) > 180){
			throw new GpsException("longitude is not in the range from -180 to 180");
		}
		
		Mapcode mapcode = MapcodeCodec.encodeToInternational(gpsCoordinates[0], gpsCoordinates[1]);
		this.mapcode = mapcode.toString();
		
		// postcondition
		assert(this.mapcode.equals(mapcode.toString()));
	}
	@Override
	public String asString()
	{
		
		// precondition
		assert(mapcode != null);
		return mapcode;
	}
}