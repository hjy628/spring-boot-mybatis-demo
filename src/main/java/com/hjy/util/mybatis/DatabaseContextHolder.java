package com.hjy.util.mybatis;

/**
 * 作用：
 * 1、保存一个线程安全的DatabaseType容器
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    // 获取数据源
    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    // 设置数据源
    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    // 清除数据源
    public static void clearDataSource() {
        contextHolder.remove();
    }
}