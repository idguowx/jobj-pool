package com.idguowx.utils.objpool;

/**
 * 已借出列表
 */
public class LinkedBorrowedObjList<E> extends LinkedPoolObjList<E> implements  BorrowedObjList<E>{
    LinkedBorrowedObjList(Class<E> cls) {
        super(cls);
    }
}
