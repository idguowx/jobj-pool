package com.idguowx.utils.objpool;

import com.idguowx.utils.objpool.exception.NoMoreFreeObj;
import com.idguowx.utils.objpool.exception.NodeNotExist;
import com.idguowx.utils.objpool.exception.NodePointerNull;

/**
 * 对象池接口
 */
public interface ObjPool<E> {
    /**
     * 从对象池获取一个空闲对象
     * @return
     * @throws NodeNotExist
     * @throws NodePointerNull
     * @throws NoMoreFreeObj 空间不足，而且无法再扩容
     */
    PoolObjNode<E> borrowObj() throws NodeNotExist, NodePointerNull, NoMoreFreeObj;

    /**
     * 归还对象到对象池
     * @param obj
     * @throws NodePointerNull
     */
    void returnObj(PoolObjNode<E> obj) throws NodePointerNull;

    /**
     * 已借出的对象数量
     * @return
     */
    int borrowedNums();

    /**
     * 空闲的对象数量
     * @return
     */
    int freeNums();

    /**
     * 清空空闲对象列表
     */
    void clearFreeObjs();
}
