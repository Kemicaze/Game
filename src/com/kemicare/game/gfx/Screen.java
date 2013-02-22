package com.kemicare.game.gfx;

public class Screen {
	//private List<Sprite> sprites = new ArrayList<Sprite>();

/*	public static final int MAP_WIDTH = 64; // Must be 2^x
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

	public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
	public int[] colors = new int[MAP_WIDTH * MAP_WIDTH];
	public int[] databits = new int[MAP_WIDTH * MAP_WIDTH];*/
	public int xOffset;
	public int yOffset;

	public static final int BIT_MIRROR_X = 0x01;
	public static final int BIT_MIRROR_Y = 0x02;

	public final int w, h;
	public int[] pixels;

	// public int[][] MultiArray;
	// public int[] ReciveArray;

	private SpriteSheet sheet;

	public Screen(int w, int h, SpriteSheet sheet) {

		this.sheet = sheet;
		this.w = w;
		this.h = h;

		pixels = new int[w * h];

		//Random random = new Random();

/*		for (int i = 0; i < MAP_WIDTH * MAP_WIDTH; i++) {

			colors[i] = Color.get(00, 40, 50, 40);
			tiles[i] = 0;
			if (random.nextInt(40) == 0) {
				tiles[i] = 32;
				colors[i] = Color.get(111, 40, 222, 333);
				databits[i] = random.nextInt(2);
			} else if (random.nextInt(40) == 0) {
				tiles[i] = 33;
				colors[i] = Color.get(20, 40, 30, 550);
			} else {
				tiles[i] = random.nextInt(4);
				databits[i] = random.nextInt(4);
			}
		}*/

		// Font.setMap("Testing The array:", this, 0, 0, Color.get(0, 555, 555,
		// 555));

		// int[][] MultiArray = new int[5][5];
		// int[] ReciveArray = new int[5];

		// for (int i = 0; i <= 4; i++) {
		// for (int n = 0; n <= 4; n++) {
		// MultiArray[i][n] = i + n;
		// }
		// }

		// String out = "";
		// for (int i = 0; i <= 4; i++) {
		// for (int n = 0; n <= 4; n++) {
		// ReciveArray[n] = MultiArray[i][n];
		// System.out.println(ReciveArray[n]);
		// }
		// for (int n = 0; n <= 4; n++) {
		// out = out + n + ": " + ReciveArray[n] + " ";
		// System.out.println(out);
		// }
		// Font.setMap(out, this, 0, i+1, Color.get(244, 100, 356, 450));
		// out = "";
		// }

		//Font.setMap("Testing The 03418791", this, 0, 0, Color.get(0, 555, 555, 555));
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 255;
		}
	}

/*	public void renderBackground() {
		for (int yt = yScroll >> 3; yt <= (yScroll + h) >> 3; yt++) {
			int yp = yt * 8 - yScroll;
			for (int xt = xScroll >> 3; xt <= (xScroll + w) >> 3; xt++) {
				int xp = xt * 8 - xScroll;
				int ti = (xt & (MAP_WIDTH_MASK)) + (yt & (MAP_WIDTH_MASK)) * MAP_WIDTH;
				render(xp, yp, tiles[ti], colors[ti], databits[ti]);
			}
		}

		for (int i = 0; i < sprites.size(); i++) {
			Sprite s = sprites.get(i);
			render(s.x, s.y, s.img, s.col, s.bits);
		}
		sprites.clear();
	}*/

	public void render(int xp, int yp, int tile, int colors, int bits) {
		xp-=xOffset;
		yp-=yOffset;
		boolean mirrorX = ((bits & BIT_MIRROR_X) > 0);
		boolean mirrorY = ((bits & BIT_MIRROR_Y) > 0);

		int xTile = tile % 32;
		int yTile = tile / 32;
		int toffs = xTile * 8 + yTile * 8 * sheet.width;

		for (int y = 0; y < 8; y++) {
			int ys = y;
			if (mirrorY)
				ys = 7 - y;
			if (y + yp < 0 || y + yp >= h)
				continue;
			for (int x = 0; x < 8; x++) {
				if (x + xp < 0 || x + xp >= w)
					continue;
				int xs = x;
				if (mirrorX)
					xs = 7 - x;
				int col = (colors >> (sheet.pixels[xs + ys * sheet.width + toffs] * 8)) & 255;
				if (col < 255)
					pixels[(x + xp) + (y + yp) * w] = col;
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {	
		this.xOffset=xOffset;
		this.yOffset=yOffset;
	}

/*	public void setTile(int x, int y, int tile, int color, int bits) {
		int tp = (x & MAP_WIDTH_MASK) + (y & MAP_WIDTH_MASK) * MAP_WIDTH;
		tiles[tp] = tile;
		colors[tp] = color;
		databits[tp] = bits;
	}*/
}