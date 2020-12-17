package com.idguowx.utils.objpool;

/**
 * 空闲队列
 */
public interface FreeObjList<E> extends PoolObjList<E> {
    /**
     * 扩展链表空间
     * @param num
     */
    void expand(int num);

    /**
     * 缩小链表空间
     * @param num
     */
    void reduce(int num);

    /**
     * 清空链表
     */
    void clear();
}
