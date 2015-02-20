package com.magicrealm.client.main;

import com.magicrealm.gui.ActivityView;
import com.magicrealm.gui.BoardView;
import com.magicrealm.models.Amazon;
import com.magicrealm.models.Character;

public class ClientMain {
	public static void main(String[] args) {
		Character character = new Amazon();
		ActivityView activityView = new ActivityView();
		BoardView board = new BoardView();
	}
}
