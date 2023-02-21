import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

/**
 * 
 * 
 * @author G0161
 * @version 5.0.2
 * 
 */

public class Playlist{
	
	private ArrayList<Song> songs;
	
	public Playlist() {
		// TODO: Initialize the songs field.
		songs = new ArrayList<Song>();
	}
	
	public Playlist(String filename) throws IOException {
		this();
		addSongs(filename);
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		else
			return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
		// TODO: Update the Lab 3 method.
		if(index >= 0 && song != null && index <= getNumSongs()) {
			songs.add(index, song);
			return true;
		}
		else
			return false;
	}
	
	public int addSongs(Playlist playlist) {
		// TODO: Update the Lab 3 method.
		if (playlist == null || playlist.getNumSongs() == 0) {
			return 0;
		}
		else {
			int count = playlist.getNumSongs();
			for (int i = 0; i < count; ++i) {
				this.addSong(playlist.getSong(i));
			}
			return count;
		}
	}
	
	public int addSongs(String filename) throws IOException {
		// TODO: Update the Lab 3 method.
		BufferedReader br = new BufferedReader(new FileReader(filename));
		int count = 0;
		String songname = "";
		while (br.readLine() != null)
			count++;
		br.close();
		int temp = count;
		BufferedReader br1 = new BufferedReader(new FileReader(filename));
		while (temp > 0) {
			songname = br1.readLine();
			Song s = new Song(songname);
			addSong(s);
			temp--;
		}
		br1.close();
		if (count > 0)
			return count;
		else
			return 0;			
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		// TODO: Update the Lab 3 method.
		if (index >= 0 && index < getNumSongs()) {
			return songs.remove(index);
		}
		return null;
	}
	
	public void saveSongs(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(toString());
		bw.close();
	}
	
	public String toString() {
		String rtn = "";
		for(Song x: songs)
			if(songs.indexOf(x) != getNumSongs() - 1)
				rtn+= x.toString() + System.lineSeparator();
			else
				rtn+= x.toString();
		return rtn;
	}
	
	public int[] getTotalTime() {
		int hrs = 0;
		int mins = 0;
		int secs = 0;
		for( Song s : songs) {
			int[] temp = Arrays.copyOf(s.getTime(), s.getTime().length);
			if (temp.length == 3) {
				hrs += temp[2];
				mins += temp[1];
				secs += temp[0];
			}
			else if (temp.length == 2) {
				mins += temp[1];
				secs += temp[0];
			}
			else
				secs += temp[0];	
		}
		mins += secs / 60;
		secs = secs % 60;
		hrs += mins / 60;
		mins = mins % 60;
		if(hrs != 0) {
			int[] s = {secs,mins,hrs};
			return s;
			}
		else if(mins != 0) {
			int[] s = {secs,mins};
			return s;
			}
		else {
			int[] s = {secs};
			return s;
			}
	}
}
