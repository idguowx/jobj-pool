package com.idguowx.utils.objpool;

import com.idguowx.utils.objpool.exception.NodeNotExist;
import com.idguowx.utils.objpool.exception.NodePointerNull;

/**
 * 池化对象链表，双向链表，头部元素不存实际值
 */
class LinkedPoolObjList <E> implements PoolObjList<E>{
    Class<E> cls;
    PoolObjNode<E> head;
    PoolObjNode<E> tail;
    int size = 0;

    LinkedPoolObjList(Class<E> cls){
        this.cls = cls;
        this.head = new PoolObjNode<E>(cls);
        this.tail = this.head;
    }

    /**
     * 从链表中删除一个节点
     * @param node
     * @return 返回被删除的节点
     * @throws NodePointerNull
     */
    public PoolObjNode<E> remove(PoolObjNode<E> node) throws NodePointerNull {
        if (size == 0){
            return node;
        }

        if (node.pre == null){
            throw new NodePointerNull("删除尾部节点:pre为null");
        }
        if (tail.equals(node)){
            tail = node.pre;
            node.pre.next = node.next;
            size --;
            return node;
        }else {
            node.pre.next = node.next;
            size --;
            return node;
        }
    }

    /**
     * 链表尾部加入节点
     * @param node
     */
    public void add(PoolObjNode<E> node) {
        node.next = null;
        node.pre = tail;
        tail.next = node;
        tail = node;
        size ++;
    }

    /**
     * 删除链表尾部节点
     * @return
     * @throws NodePointerNull
     * @throws NodeNotExist
     */
    public PoolObjNode<E> removeTail() throws NodePointerNull, NodeNotExist {
        if (size == 0){
            throw new NodeNotExist();
        }
        return remove(tail);
    }

    public int size() {
        return size;
    }
}
