package mati.nave.levels;

import mati.nave.land.Tile;

import java.lang.reflect.InvocationTargetException;

public class LevelGenerator {
	public static Tile[][] generate(Tile[][] arr)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Tile[][] temp = new Tile[arr.length][arr[0].length];
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[x].length; y++) {
				Class<? extends Tile> clazz = arr[x][y].getClass();
				Tile t = clazz.cast(clazz.newInstance());
				t.setBounds(y * 64, x * 64, 64, 64);
				temp[x][y] = t;
			}
		}
		return temp;
	}
}
