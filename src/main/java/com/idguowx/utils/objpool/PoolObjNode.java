package com.idguowx.utils.objpool;

/**
 * 池化对象
 */
public class PoolObjNode<E> {
    /**
     * 保存的对象的类型
     */
    private Class<E> cls;
    /**
     * 保存的对象
     */
    private E item;
    /**
     * 节点的上一个节点指针
     */
    PoolObjNode<E> pre;
    /**
     * 节点的下一个节点指针
     */
    PoolObjNode<E> next;

    PoolObjNode(PoolObjNode<E> pre,PoolObjNode<E> next ,Class<E> cls){
        this.cls = cls;
        this.pre = pre;
        this.next = next;
        this.item = buildElement();
    }
    PoolObjNode(Class<E> cls){
        this.cls = cls;
        this.item = buildElement();
    }

    private PoolObjNode(E e){
        this.item = e;
    }

    public static<E> PoolObjNode<E> build(E v){
        return new PoolObjNode<E>(v);
    }

    private E buildElement(){
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public E getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }
}
