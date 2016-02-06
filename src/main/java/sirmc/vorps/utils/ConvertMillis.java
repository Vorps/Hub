package sirmc.vorps.utils;

public class ConvertMillis {
	private static long jours;
	private static long heures;
	private static long minutes;
	private static long secondes;
	private static String messageTemps;
	
	public static String ConvertMillisToDate(long time){
		messageTemps = "";
		if(time >= 86400000L){
			jours = time / 86400000L;
			if(jours == 1){
				messageTemps = jours+" Jour ";
			} else {
				messageTemps = jours+" Jours ";
			}
			time-=86400000L*jours;
		}
		if(time < 86400000L && time >= 3600000L){
			heures = time / 3600000L;
			if(heures == 1){
				messageTemps+= heures+" Heure ";
			} else {
				messageTemps+= heures+" Heures ";
			}
			time-= 3600000L*heures;
		}
		if(time < 3600000L && time >= 60000L){
			minutes = time / 60000L;
			if(minutes == 1){
				messageTemps+= minutes+" Minute ";
			} else {
				messageTemps+= minutes+" Minutes ";
			}
			time-= 60000L*minutes;
		}
		if(time < 60000L && time >= 1000L){
			secondes = time / 1000L;
			if(secondes == 1){
				messageTemps+= secondes+" seconde ";
			} else {
				messageTemps+= secondes+" secondes ";
			}
			time-= 1000L*secondes;
		}
		return messageTemps;
	}
}
