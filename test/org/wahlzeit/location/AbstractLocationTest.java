package org.wahlzeit.location;

import junit.framework.TestCase;


public class AbstractLocationTest extends TestCase {


	protected String mapcode = "XFNT9.JFFB";
	protected double lat = 64.395844;
	protected double lon = -21.241131;
	protected Location mapcodeLocation;
	protected Location gpsLocation;
	

	public void setUp() {
		mapcodeLocation = new MapcodeLocation(mapcode);
		try {
			gpsLocation= new GpsLocation(lat, lon);
		} catch (GpsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void testGetLocationType(){
		String string1 = mapcodeLocation.getLocationType();
		String string2 = gpsLocation.getLocationType();
		assertTrue("location type of mapcodeLocation should be " + "Mapcode" + " but was " + string1 + " and location type of gpsLocation should be " +
				"GPS Coordinates" + " but was " + string2, (string1.equals("Mapcode")) && (string2.equals("GPS Coordinates")));
	}
	
	public final void testGetMapcode() {
		String string1 = mapcodeLocation.getMapcode();
		String string2 = gpsLocation.getMapcode();
		assertTrue("mapcode of mapcodeLocation should be " + mapcode + " but was " + string1 + " and mapcode of gpsLocation should be " +
				mapcode + " but was " + string2, (mapcode.equals(string1)) && (mapcode.equals(string2)));
	}

	public final void testGetGpsCoordinates() {
		double[] double1 = mapcodeLocation.getGpsCoordinates();
		double[] double2 = gpsLocation.getGpsCoordinates();
		assertTrue("gps coordinates of mapcodeLocation should be " + lat + ", " + lon + " but was " + double1[0] + ", " + double1[1] + 
				" and gps coordinates of gpsLocation should be " + lat + ", " + lon + " but was " + double2[0] + ", " + double2[1], (lat == double1[0]) && (lon == double1[1]) && (lat == double2[0]) && (lon == double2[1]));
	}

	
	public final void testSetMapcode() {
		String newMapcode = "VJFLZ.3ZR3";
		mapcodeLocation.readInMapcode(newMapcode);
		gpsLocation.readInMapcode(newMapcode);
		assertTrue("mapcode of mapcodeLocation should be " + newMapcode + " but was "  + ", " + mapcodeLocation.getMapcode() + 
				" and gps coordinates of gpsLocation should be " + newMapcode + " but was "  + ", " + gpsLocation.getMapcode(), (newMapcode.equals(mapcodeLocation.getMapcode())) && (newMapcode.equals(gpsLocation.getMapcode())));
	
	}


	public final void testSetGpsCoordinatesDoubleArray() {
		double lat = 49.573802;
		double lon = 11.028629;
		double[] gpsCoordinates = new double[]{lat, lon};
		try {
			gpsLocation.readInGpsCoordinates(gpsCoordinates);
		} catch (GpsException e) {
			e.printStackTrace();
		}
		try {
			mapcodeLocation.readInGpsCoordinates(gpsCoordinates);
		} catch (GpsException e) {
			e.printStackTrace();
		}
		assertTrue("gps coordinates of mapcodeLocation should be " + lat + ", " + lon + " but was " + mapcodeLocation.getGpsCoordinates()[0] + ", " + mapcodeLocation.getGpsCoordinates()[1] + 
				" and gps coordinates of gpsLocation should be " + lat + ", " + lon + " but was " + gpsLocation.getGpsCoordinates()[0] + ", " + gpsLocation.getGpsCoordinates()[1], (lat == mapcodeLocation.getGpsCoordinates()[0]) && (lon == mapcodeLocation.getGpsCoordinates()[1]) && (lat == gpsLocation.getGpsCoordinates()[0]) && (lon == gpsLocation.getGpsCoordinates()[1]));
	}

	public final void testSetGpsCoordinatesDoubleDouble() {
		double lat = 49.573802;
		double lon = 11.028629;
		try {
			gpsLocation.readInGpsCoordinates(lat, lon);
		} catch (GpsException e) {
			e.printStackTrace();
		}
		try {
			mapcodeLocation.readInGpsCoordinates(lat, lon);
		} catch (GpsException e) {
			e.printStackTrace();
		}
		assertTrue("gps coordinates of mapcodeLocation should be " + lat + ", " + lon + " but was " + mapcodeLocation.getGpsCoordinates()[0] + ", " + mapcodeLocation.getGpsCoordinates()[1] + 
				" and gps coordinates of gpsLocation should be " + lat + ", " + lon + " but was " + gpsLocation.getGpsCoordinates()[0] + ", " + gpsLocation.getGpsCoordinates()[1], (lat == mapcodeLocation.getGpsCoordinates()[0]) && (lon == mapcodeLocation.getGpsCoordinates()[1]) && (lat == gpsLocation.getGpsCoordinates()[0]) && (lon == gpsLocation.getGpsCoordinates()[1]));
	}
	
	public final void testAsString() {
		String gpsString = gpsLocation.asString();
		String mapcodeString = mapcodeLocation.getMapcode();
		assertTrue("GPS location as string should be '" + lat + " " + lon + "' but was '" + gpsString + "'",
				gpsString.equals(lat + " " + lon));
		
		assertTrue("Mapcode location as string should be '" + mapcode + "' but was '" + mapcodeString + "'",
		mapcodeString.contains(mapcode));
	}
}
