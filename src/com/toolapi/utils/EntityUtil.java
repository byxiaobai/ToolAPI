package com.toolapi.utils;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class EntityUtil {
	public static void removeWorldEntityByType(World world,EntityType type) {
		List<LivingEntity> entities=world.getLivingEntities();
		for(LivingEntity entity:entities) {
			if(entity.getType()==type) {
				entity.remove();
			}
		}
	}
}
