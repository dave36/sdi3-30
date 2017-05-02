package com.sdi.client.util;

import java.util.List;

import alb.util.console.Console;

public class Util {
	
	public static void printLista(List<String> lista){
		Console.println("------- Tareas de Hoy y atrasadas -------");
		for(String t: lista){
			Console.println(t);
		}
	}
}
