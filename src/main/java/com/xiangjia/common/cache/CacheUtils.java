package com.xiangjia.common.cache;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.dict.model.DictModel;
import com.base.dictitem.mapper.DictItemMapper;
import com.base.dictitem.model.DictItemModel;
import com.xiangjia.base.mapper.BaseMapper;
import com.xiangjia.base.model.BaseModel;
import com.xiangjia.common.helper.SpringContextHelper;

public class CacheUtils {

	private static Logger log = LoggerFactory.getLogger(CacheUtils.class);

	private static final String CACHE_CONFIG_PATH = "/com/mck/config/cache/ehcache-base.xml";

	private static final String DEFAULT_CACHE_NAME = "systemCache";

	private static Map<String, Object> paramsMap = new HashMap<String, Object>();

	private static DictItemMapper dictItemMapper = null;

	private static CacheManager manager = null;

	private static Cache cache = null;

	// 获取cache
	private static Cache getCache() {
		if (manager == null) {
			String url = (CacheUtils.class.getResource("/").getPath());
			manager = CacheManager.create(url + CACHE_CONFIG_PATH);
		}
		if (cache == null) {
			cache = manager.getCache(DEFAULT_CACHE_NAME);
		}
		return cache;
	}

	// 新增普通缓存
	public static void addCache(BaseModel model) {
		if (model == null) {
			return;
		}
		Cache cache = CacheUtils.getCache();
		String key = getSystemKey(model);
		if (cache.isKeyInCache(key)) {
			cache.remove(key);
		}
		Element e = new Element(key, model);
		cache.put(e);
	}

	// 新增数据字典缓存
	public static void addCache(DictItemModel model) {
		if(model != null){
			DictModel dictModel = getCache(model.getDictId(), DictModel.class);
			String key = getDictKey(model.getItemCode() + "", dictModel.getDictCode());
			Cache cache = CacheUtils.getCache();
			if (cache.isKeyInCache(key)) {
				cache.remove(key);
			}
			Element e = new Element(key, model);
			cache.put(e);
		}
	}

	// 获取数据字典缓存
	public static DictItemModel getCache(String dictCode, Object value) {
		if (value == null || dictCode == null) {
			return new DictItemModel();
		}
		String key = getDictKey(value.toString(), dictCode);

		Cache cache = getCache();
		Element e = cache.get(key);
		DictItemModel model = null;
		if (e != null) {
			model = (DictItemModel) e.getObjectValue();
		} else {
			model = getDictItemModel(value, dictCode);
			log.info("从数据库获取:" + model);
		}
		return model;
	}

	// 查询数据字典条目对象
	private static DictItemModel getDictItemModel(Object value, String dictCode) {
		if (dictItemMapper == null) {
			dictItemMapper = SpringContextHelper.getBean(DictItemMapper.class);
		}
		paramsMap.clear();
		paramsMap.put("itemCode", value);
		paramsMap.put("orderBy", " and dict_id = (select max(id) from t_base_dict where dict_code = '" + dictCode + "' and active = 1)");
		DictItemModel model = null;
		try {
			model = dictItemMapper.queryModelByParams(paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (model == null) {
			model = new DictItemModel();
		}
		addCache(model);
		return model;
	}

	// 获取普通缓存
	@SuppressWarnings("unchecked")
	public static <T extends BaseModel> T getCache(Object value, Class<T> clazz) {
		if (value == null) {
			try {
				return getNewInstance(clazz);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String key = getSystemKey(clazz, value);
		Cache cache = CacheUtils.getCache();
		Element e = cache.get(key);
		T model = null;
		if (e != null) {
			model = (T) e.getObjectValue();
		} else {
			try {
				model = getModel(value, clazz);
				log.info("从数据库获取:" + model);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return model;
	}

	// 获取数据字典缓存的key
	private static String getDictKey(String key, String dictCode) {
		return dictCode + "_" + key;
	}

	// 获取普通缓存的key
	private static String getSystemKey(BaseModel model) {
		return getSystemKey(model.getClass(), model.getId());
	}

	// 获取普通缓存的key
	@SuppressWarnings("rawtypes")
	private static String getSystemKey(Class clazz, Object key) {
		return clazz.getSimpleName() + "_" + key;
	}

	// 查询普通对象model
	@SuppressWarnings("unchecked")
	private static <T extends BaseModel> T getModel(Object value, Class<T> clazz) throws Exception {
		BaseMapper<BaseModel> mapper = getMapper(clazz);
		if (mapper == null || value == null) {
			return null;
		}
		T model = (T) mapper.queryModelById(Long.parseLong(value.toString()));
		if (model != null) {
			addCache(model);// 将查询到的数据放入缓存中
		} else {
			model = getNewInstance(clazz);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	private static <T extends BaseModel> T getNewInstance(Class<T> clazz) throws Exception {
		String className = clazz.toString().replace("class", "").replace(" ", "");
		T model = (T) Class.forName(className).newInstance();
		return model;
	}

	// 获取普通缓存mapper
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T extends BaseMapper<BaseModel>> T getMapper(Class clazz) {
		String simpleName = clazz.getSimpleName();
		simpleName = simpleName.replaceFirst(simpleName.charAt(0) + "", (simpleName.charAt(0) + "").toLowerCase());
		simpleName = simpleName.replace("Model", "Mapper");
		Object obj = (SpringContextHelper.getBean(simpleName));
		return (T) obj;
	}
}
