package mati.nave.levels;

import mati.nave.land.Land;

import java.lang.reflect.InvocationTargetException;

public class LevelGenerator {
	public static Land[][] generate(Class[][] arr)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Land[][] temp = new Land[arr[0].length][arr.length];
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[x].length; y++) {
				@SuppressWarnings("unchecked")
				Class<? extends Land> clazz = arr[x][y];
				Land t = clazz.cast(clazz.newInstance());
				int size = 25;
				t.setBounds(y * size, x * size, size, size);
				temp[y][x] = t;
			}
		}
		return temp;
	}
}
