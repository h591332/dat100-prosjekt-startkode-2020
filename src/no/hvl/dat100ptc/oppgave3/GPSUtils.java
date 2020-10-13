package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.math.BigDecimal;
import java.math.MathContext;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;


		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] lat= new double[gpspoints.length];
		for(int i=0; i<lat.length; i++) {
			lat[i]= gpspoints[i].getLatitude();
		}
		return lat;
		
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] lon= new double[gpspoints.length];
		for(int i=0; i<lon.length; i++) {
			lon[i]= gpspoints[i].getLongitude();
		}
		return lon;
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		// TODO - START
		
		latitude1= gpspoint1.getLatitude();
		latitude2= gpspoint2.getLatitude();
		longitude1= gpspoint1.getLongitude();
		longitude2= gpspoint2.getLongitude();
		/* Coden under fungerte ikke?
		double lat1=Math.toRadians(latitude1);
	    double lat2=Math.toRadians(latitude2);
	    double lon1=Math.toRadians(longitude1);
	    double lon2=Math.toRadians(latitude1);
		double dlat=Math.toRadians(lat2-lat1);
		double dlon=Math.toRadians(longitude2-longitude1);
		
		double tmp1 = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.cos(lat1) * Math.cos(lat2) *
		Math.sin(dlon/2) * Math.sin(dlon/2);
		double tmp2=2 * Math.atan2(Math.sqrt(tmp1), Math.sqrt(1-tmp1));
		d= R*tmp2;
		*/
		double φ1 = latitude1 * Math.PI/180; // φ, λ in radians
		double φ2 = latitude2 * Math.PI/180;
		double Δφ = (latitude2-latitude1) * Math.PI/180;
		double Δλ = (longitude2-longitude1) * Math.PI/180;

		double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
		          Math.cos(φ1) * Math.cos(φ2) *
		          Math.sin(Δλ/2) * Math.sin(Δλ/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		d = R * c; // in metres
		return d;
		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		secs = gpspoint2.getTime()-gpspoint1.getTime();
		double d1=distance(gpspoint1,gpspoint2);
		double d2= d1/secs;
		speed= d2*18/5;
		
		return speed;
		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		// TODO - START
		int h= secs/3600;
		int m = (secs % 3600)/60;
		int s = secs%60;
		timestr="  "+String.format("%02d:%02d:%02d", h,m,s);
		return timestr;
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		BigDecimal b1= new BigDecimal(d);
		MathContext m=new MathContext(3);
		BigDecimal b2= b1.round(m);
		String tmp=b2.toString();
		str=tmp;
		while(str.length()<TEXTWIDTH) {
			str=" "+str;
		}
		return str;


		// TODO - SLUTT
		
	}
}
