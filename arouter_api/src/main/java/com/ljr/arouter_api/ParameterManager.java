package com.ljr.arouter_api;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.LruCache;

import com.ljr.arouter_api.core.ParameterLoad;

/**
 * 参数Parameter加载管理器
 */
public class ParameterManager {
    private static ParameterManager instance;
    //Lru缓存，key:类名，value:参数Parameter加载接口
    private LruCache<String, ParameterLoad> cache;
    //APT生成的获取参数源文件，后缀名
    private static final String FILE_SUFFIX_NAME = "$$Parameter";

    //单例
    public static ParameterManager getInstance() {
        if (instance == null) {
            synchronized (ParameterManager.class) {
                if (instance == null) {
                    instance = new ParameterManager();
                }
            }
        }
        return instance;
    }

    private ParameterManager() {
        cache =new LruCache<>(163);
    }

    /**
     * 传入的Activity中所有被@Parameter注解的属性。通过加载APT生成源文件，并给属性赋值
     * @param activity 需要给属性赋值的类，如：MianActivity中所有被@Parameter注解的属性
     */
   public void loadParameter(@NonNull Activity activity){
       String className = activity.getClass().getName();
       //查找缓存集合中是否有对应activity的value
       ParameterLoad iParameter = cache.get(className);
       try{
           //找不到，加载类后放入缓存集合
           if(iParameter == null){
               //注意：这里的className是全类名：com.xxx.xxx.xx.xxActivity
               Class<?> clazz = Class.forName(className + FILE_SUFFIX_NAME);
               iParameter = (ParameterLoad) clazz.newInstance();
               cache.put(className, iParameter);
           }
           //通过传入参数给生成的源文件中所有属性赋值
           iParameter.loadParameter(activity);
       }catch (Exception e){
           e.printStackTrace();
       }
   }

}
