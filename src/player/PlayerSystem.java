/**
 * this class is for reading and manipulating csv file.
 */
package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class PlayerSystem {
	
	private ArrayList<Player> players = new ArrayList<>();

	
	
	
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	
	/**
	 * this method scans The csv file, initialize each player according to their position and adds them into
	 * players ArrayList
	 * @param filePath
	 */
	public void scanCsv(String filePath) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
			
			String line;
			
			while ((line = br.readLine()) != null) {
				
				
				String[] elements = line.split(";");
				
				if (elements.length > 0) {
					
					if ("C".equals(elements[2])) {
						
						players.add(new PlayerC(elements[1], Double.parseDouble(elements[29]),
								Double.parseDouble(elements[23]),Double.parseDouble(elements[24]),
								Double.parseDouble(elements[26]),Double.parseDouble(elements[25])));
					}
					
					else if ("PF".equals(elements[2])) {
						
						players.add(new PlayerPF(elements[1], Double.parseDouble(elements[29]),
								Double.parseDouble(elements[23]),Double.parseDouble(elements[24]),
								Double.parseDouble(elements[26]),Double.parseDouble(elements[25])));
						
					}
					
					else if ("PG".equals(elements[2])) {
						
						players.add(new PlayerPG(elements[1], Double.parseDouble(elements[29]),
								Double.parseDouble(elements[23]),Double.parseDouble(elements[24]),
								Double.parseDouble(elements[26]),Double.parseDouble(elements[25])));
						
					}
					
					else if ("SF".equals(elements[2])) {
						
						players.add(new PlayerSF(elements[1], Double.parseDouble(elements[29]),
								Double.parseDouble(elements[23]),Double.parseDouble(elements[24]),
								Double.parseDouble(elements[26]),Double.parseDouble(elements[25])));
						
					}
					
					else if ("SG".equals(elements[2])) {
						
						players.add(new PlayerSG(elements[1], Double.parseDouble(elements[29]),
								Double.parseDouble(elements[23]),Double.parseDouble(elements[24]),
								Double.parseDouble(elements[26]),Double.parseDouble(elements[25])));
						
					}
					
					
				}
			}
			
			
			
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * this method is for removing duplicate elements. I create an additional ArrayList<Player>
	 * while iterating over players, I check if that player element is inside the playerCount. If it is, do nothing since
	 * that means I already put that player into playerCOunt. If it is not inside the playerCount, by creating another
	 * for loop, check every same named player and get the average of all of them. If there is only one player with that name
	 * it still gonna be the same player with same stats since I divide his stats with 1 (count value).
	 * at the end, set players ArrayList as playerCount ArrayList.
	 */
	public void removeDuplicates() {
		
		ArrayList<Player> playerCount = new ArrayList<>();
		
		for(Player player : players) {
			
			boolean check = true;
			
			double pts = 0, trb = 0, ast = 0, blk = 0, stl = 0;
			int count = 0;
			
			for(Player i : playerCount) {
				
				if(i.getPlayerName().equals(player.getPlayerName())) {
					
					check = false;
				}
			}
			
			
			if(check) {
				
				for(Player player1 : players) {
					
					if(player1.getPlayerName().equals(player.getPlayerName())) {
						
						pts += player1.getPts();
						trb += player1.getTrb();
						ast += player1.getAst();
						blk += player1.getBlk();
						stl += player1.getStl();
						
						count++;
						
					}
				}

				player.setAst(Double.parseDouble(String.format(Locale.US, "%.2f" ,ast/count)));
				player.setBlk(Double.parseDouble(String.format(Locale.US, "%.2f" ,blk/count)));
				player.setPts(Double.parseDouble(String.format(Locale.US, "%.2f" ,pts/count)));
				player.setStl(Double.parseDouble(String.format(Locale.US, "%.2f" ,stl/count)));
				player.setTrb(Double.parseDouble(String.format(Locale.US, "%.2f" ,trb/count)));
			
				
				playerCount.add(player);
			}
			
		}
		
		players.clear();
		players.addAll(playerCount);
		
	}
	


	

}
