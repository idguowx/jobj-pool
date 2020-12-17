package com.idguowx.utils.objpool;

import com.idguowx.utils.objpool.exception.NodeNotExist;
import com.idguowx.utils.objpool.exception.NodePointerNull;

/**
 * 池化对象列表
 */
public interface PoolObjList<E> {
    /**
     * 新增节点
     * @param node
     */
    void add(PoolObjNode<E> node);

    /**
     * 删除节点
     * @param node
     * @return
     * @throws NodePointerNull
     */
    PoolObjNode<E> remove(PoolObjNode<E> node) throws NodePointerNull;

    /**
     * 删除尾部节点
     * @return
     * @throws NodePointerNull
     * @throws NodeNotExist
     */
    PoolObjNode<E> removeTail() throws NodePointerNull, NodeNotExist;

    /**
     * 元素个数
     * @return
     */
    int size();
}
