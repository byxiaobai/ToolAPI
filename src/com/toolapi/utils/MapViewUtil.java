package com.toolapi.utils;

import java.awt.Rectangle;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

/** 
* @author byxiaobai
* 关于地图绘画的工具类
*/
public class MapViewUtil {
	private static final HashMap<MapView,MapRenderer> MAP_RENDERER_CACHE=new HashMap<>();
	/**
	 * 在地图上绘制一个矩形
	 */
	public static void drawRectangle(MapView mapView,Rectangle mRect,byte color) {
		MapRenderer oldRenderer=MAP_RENDERER_CACHE.get(mapView);
		if(oldRenderer!=null) {
			mapView.removeRenderer(oldRenderer);
		}
		MapRenderer newRenderer=new MapRenderer() {
			@Override
			public void render(MapView arg0, MapCanvas canvas, Player arg2) {
				int width=mRect.width;
				int height=mRect.height;
				for(int i=0;i<width;i++) {//上
					canvas.setPixel(mRect.x+i, mRect.y, (byte) color);
				}
				for(int i=0;i<height;i++) {//下
					canvas.setPixel(mRect.x+i, mRect.y+height, (byte) color);
				}
				for(int i=0;i<width;i++) {//左
					canvas.setPixel(mRect.x,mRect.y+i, (byte) color);
				}
				for(int i=0;i<height;i++) {//右
					canvas.setPixel(mRect.x+width, mRect.y+i, (byte) color);
				}
				canvas.setPixel(mRect.x+width, mRect.y+height, color);
			}
		};
		MAP_RENDERER_CACHE.put(mapView, newRenderer);
		mapView.addRenderer(newRenderer);
	}
	public static void drawPlayer(MapView mapView,Player player) {
		MapRenderer oldRenderer=MAP_RENDERER_CACHE.get(mapView);
		if(oldRenderer!=null) {
			mapView.removeRenderer(oldRenderer);
		}
		MapRenderer newRenderer=new MapRenderer() {
			@Override
			public void render(MapView arg0, MapCanvas canvas, Player arg2) {
				MapCursorCollection cursors = canvas.getCursors();
				cursors.addCursor(0,0,(byte)1);
			}
		};
		MAP_RENDERER_CACHE.put(mapView, newRenderer);
		mapView.addRenderer(newRenderer);
	}
}
