package com.idguowx.utils.objpool;

/**
 * 空闲列表
 */
public class LinkedFreeObjList<E> extends LinkedPoolObjList<E> implements FreeObjList<E> {

    LinkedFreeObjList(Class<E> cls) {
        super(cls);
    }

    public void expand(int num) {
        while (num-- > 0){
            PoolObjNode<E> e = new PoolObjNode<E>(cls);
            this.add(e);
        }
    }

    public void reduce(int num) {
        if (size == 0){
            return;
        }
        if (size() < num ){
            num = size();
        }

        PoolObjNode<E> cur = tail;
        while (num-- > 0){
            cur = cur.pre;
        }

        cur.pre.next = null;
        tail = cur.pre;
    }

    public void clear() {
        this.head = new PoolObjNode<E>(cls);
        this.tail = this.head;
        this.size = 0;
    }
}
