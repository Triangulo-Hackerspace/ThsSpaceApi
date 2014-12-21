package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Cache;

public class CacheUtil {

	private static final String SCHEDULE = "m.02";
	
	private CacheUtil() {
	}

	public static Cache createCache() {
		return getCache();
	}

	public static List<Cache> createCacheList(int howMany) {
		List<Cache> cacheList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			cacheList.add(getCache());
		}
		return cacheList;
	}
	
	public static Cache getCache(){
		return new Cache(SCHEDULE, SpaceUtil.getSpace());
	}
}
