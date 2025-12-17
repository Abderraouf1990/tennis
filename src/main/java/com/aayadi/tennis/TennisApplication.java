package com.aayadi.tennis;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisApplication {

	public static void main(String[] args) {
		TennisGame.play("ABABABABBB");
	}

}
