package com.hotel.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CompareDate {

	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
    }
	//đổi Date sql sang localDate:  compareDate.CompareDate(roomDto.getCheckinDate().toLocalDate())
	public boolean CompareDate(LocalDate CheckinDate) {
		LocalDate now = LocalDate.now();
		if(CheckinDate.compareTo(now)<0) {
			return false;
		}
		else {
			return true;
		}
		
	}
}
