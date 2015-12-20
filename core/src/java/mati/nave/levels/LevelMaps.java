package mati.nave.levels;

import mati.nave.land.Path;
import mati.nave.land.Tile;
import mati.nave.land.Wall;

public class LevelMaps {
	private static Path p = new Path();
	private static Wall w = new Wall();

	// @formatter:off
public static Tile[][] l1 = {
{w,w,w,w,w,w,w,w,w,w},
{w,p,p,p,p,p,p,p,p,w},
{w,w,w,w,w,w,w,w,w,w}
};
    // @formatter:on
}
