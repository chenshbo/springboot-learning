package com.chenshbo.redis.mapper;

import com.chenshbo.entity.User;
import org.springframework.util.StringUtils;
import redis.clients.jedis.ShardedJedis;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 需要在Redis中进行存取的vo
 */
public class CacheVo<T> implements Serializable, IMapper {

    private static final long serialVersionUID = 1L;

    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public CacheVo(T obj) {
        this.obj = obj;
    }

    public CacheVo(T obj,Map<String, String> kv) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                //获取属性的名字
                String name = field.getName();
                String value = kv.get(name);
                if (!StringUtils.isEmpty(value)) {
                    //将属性的首字符大写，方便构造get，set方法
                    String setMethod = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    //获取属性的类型
                    String fieldType = field.getType().getSimpleName();
                    //如果type是类类型，则前面包含"class "，后面跟类名
                    Method fieldSetMet = obj.getClass().getMethod(setMethod, field.getType());
                    if ("String".equals(fieldType)) {
                        fieldSetMet.invoke(obj, value);
                    } else if ("Date".equals(fieldType)) {
                        fieldSetMet.invoke(obj, new Date(Long.parseLong(value)));
                    } else if ("Integer".equals(fieldType)
                            || "int".equals(fieldType)) {
                        fieldSetMet.invoke(obj, Integer.parseInt(value));
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        fieldSetMet.invoke(obj, Long.parseLong(value));
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        fieldSetMet.invoke(obj, Double.parseDouble(value));
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value);
                        fieldSetMet.invoke(obj, temp);
                    } else {
                        System.out.println("not supper type" + fieldType);
                    }
                }
            }
            this.obj = obj;
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String key() {
        try {
            // 类名
            String simpleName = obj.getClass().getSimpleName();
            simpleName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
            // id属性的值
            Method m = obj.getClass().getMethod("getId");
            String idValue = String.valueOf(m.invoke(obj));
            return simpleName + ":" + idValue;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void save(ShardedJedis jedis) {
        String key = key();
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                //获取属性的名字
                String fieldName = field.getName();
                //将属性的首字符大写，方便构造get，set方法
                String getMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                //获取属性的类型
                String fieldType = field.getType().getSimpleName();
                //如果type是类类型，则前面包含"class "，后面跟类名
                Method m = obj.getClass().getMethod(getMethod);
                if ("Date".equals(fieldType)) {
                    Date value = (Date) m.invoke(obj);
                    if (value != null) {
                        jedis.hset(key, fieldName, String.valueOf(value.getTime()));
                    }
                } else {
                    String value = String.valueOf(m.invoke(obj));
                    if (value != null) {
                        jedis.hset(key, fieldName, value);
                    }
                }
            }
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
