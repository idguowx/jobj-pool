package com.idguowx.utils.objpool;

import com.idguowx.utils.objpool.exception.PoolParamsError;
/**
 * 工厂类
 */
public class PoolFactory<E> {
    /**
     * 获取默认的对象池实例
     * （1）空闲数量不足每次新增固定个数的空闲数量
     * （2）不支持动态缩容
     * @param initFreeSize
     * @param cls
     * @param <E>
     * @return
     * @throws PoolParamsError
     */
    public static<E> ObjPool<E> getDefaultObjPool(int initFreeSize , Class<E> cls) throws PoolParamsError {
        return new LinkedObjPool<E>(initFreeSize, new DefaultFreeSizeAdjuster(),cls);
    }
}
