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
		locationType = "Mapcode";
		this.mapcode = mapcode;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownMapcodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gpsCoordinates;
	}
	@Override
	public String getMapcode()
	{
		return mapcode;
	}
	@Override
	public void readInMapcode(String mapcode)
	{
		this.mapcode = mapcode;
	}
	@Override
	protected void doReadInGpsCoordinates(double[] gpsCoordinates)
	{
		Mapcode mapcode = MapcodeCodec.encodeToInternational(gpsCoordinates[0], gpsCoordinates[1]);
		this.mapcode = mapcode.toString();
	}
	@Override
	public String asString()
	{
		return mapcode;
	}
}