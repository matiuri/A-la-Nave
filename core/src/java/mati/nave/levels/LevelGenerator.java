package mati.nave.levels;

import mati.nave.land.Tile;

import java.lang.reflect.InvocationTargetException;

public class LevelGenerator {
	public static Tile[][] generate(Class[][] arr)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Tile[][] temp = new Tile[arr[0].length][arr.length];
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[x].length; y++) {
				@SuppressWarnings("unchecked")
				Class<? extends Tile> clazz = arr[x][y];
				Tile t = clazz.cast(clazz.newInstance());
				int size = 25;
				t.setBounds(y * size, x * size, size, size);
				temp[y][x] = t;
			}
		}
		return temp;
	}
}
