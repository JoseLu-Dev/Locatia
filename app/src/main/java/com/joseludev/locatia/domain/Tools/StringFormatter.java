package com.joseludev.locatia.domain.Tools;

public class StringFormatter {
    public static String distanceFormatter(double distanceKm){
        if(distanceKm < 1){
            int distanceM = (int) (distanceKm * 1000);
            return distanceM + "m";
        }else{
            return (int) (distanceKm) + "Km";
        }
    }
}
