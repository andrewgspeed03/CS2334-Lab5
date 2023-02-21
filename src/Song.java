import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info) {
		String[] splitInfo = info.split(Song.INFO_DELIMITER);
		this.title = splitInfo[0];
		this.artist = splitInfo[1];
		
		String[] splitTime = splitInfo[2].split(TIME_DELIMITER);
		int [] temp = new int[splitTime.length];
		for (int i = 0; i < temp.length; i++) 
	        temp[i] = Integer.parseInt(splitTime[splitTime.length - 1 - i]);
		this.time = Arrays.copyOf(temp, temp.length);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	
	public String toString() {
		String ret = getTitle() + INFO_DELIMITER +getArtist() +INFO_DELIMITER;
		int [] time = getTime();
		int len = time.length;
			for(int x = len-1; x >= 0; x-- ) {
				if (x == len-1)
					ret+= time[x];
				else
					ret += String.format(":%02d", time[x]);
			}
		
		return ret;
			
	}
}
