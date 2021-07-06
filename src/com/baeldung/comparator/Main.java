package com.baeldung.comparator;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Player> list = Arrays.asList(new Player(40, "shailesh", 30), new Player(10, "sachin", 35));
		System.out.println(list);
//		Collections.sort(list);
		
//		Collections.sort(list, new Comparator<Player>() {
//			@Override
//			public int compare(Player o1, Player o2) {
//				return o1.getRanking() - o2.getRanking();
//			}
//		});
//		
		
		Collections.sort(list, (p1, p2) -> p1.getRanking() - p2.getRanking()); 
		
		list.sort(Comparator.comparing(Player::getRanking));
		
		System.out.println(list);
		
		Calendar.Builder builder = new Calendar.Builder();
		builder.setInstant(new Date());
		builder.build();
		
	}
}	
