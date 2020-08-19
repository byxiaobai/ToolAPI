package com.toolapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class EntityUtil {
	private static final double INF=Double.MAX_VALUE;
	
	public static void removeWorldEntityByType(World world,EntityType type) {
		List<LivingEntity> entities=world.getLivingEntities();
		for(LivingEntity entity:entities) {
			if(entity.getType()==type) {
				entity.remove();
			}
		}
	}
	
	
	/**
	 * 获取范围内距实体最近的玩家
	 * @param entity 实体
	 * @param range 范围
	 * @return
	 */
	public static Player getNearestPlayerInRange(Entity entity,double range) {
		if(entity==null)return null;
		Location eLoc=entity.getLocation();
		List<Entity> entities=entity.getNearbyEntities(range, range, range);
		double pDis=INF;
		Player player=null;
		for(Entity en:entities) {
			if(en instanceof Player) {
				double tmpDis=en.getLocation().distance(eLoc);
				if(tmpDis<pDis) {
					player=(Player)en;
					pDis=tmpDis;
				}
			}
		}
		return player;
	}
	
}
