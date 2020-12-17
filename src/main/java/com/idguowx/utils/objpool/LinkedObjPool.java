package com.idguowx.utils.objpool;

import com.idguowx.utils.objpool.exception.NoMoreFreeObj;
import com.idguowx.utils.objpool.exception.NodeNotExist;
import com.idguowx.utils.objpool.exception.NodePointerNull;
import com.idguowx.utils.objpool.exception.PoolParamsError;

/**
 * 链表对象池
 */
public class LinkedObjPool<E> implements ObjPool<E> {
    /**
     * 已经借出的对象列表
     */
    private BorrowedObjList<E> borrowedObjs;
    /**
     * 空闲的对象列表
     */
    private FreeObjList<E> freeObjs;
    /**
     * 初始的空闲对象列表容量
     */
    private int initFreeSize;
    /**
     * 空闲对象空间调整器
     */
    private FreeSizeAdjuster freeSizeAdjuster;

    LinkedObjPool(int initFreeSize, FreeSizeAdjuster freeSizeAdjuster, Class<E> cls) throws PoolParamsError {
        if (initFreeSize <= 0){
            throw new PoolParamsError("initFreeSize必须大于0");
        }
        if (freeSizeAdjuster == null){
            throw new PoolParamsError("freeSizeAdjuster不能为null");
        }

        this.freeObjs = new LinkedFreeObjList<E>(cls);
        this.borrowedObjs = new LinkedBorrowedObjList<E>(cls);
        this.freeSizeAdjuster = freeSizeAdjuster;
        this.initFreeSize = initFreeSize;
        this.freeObjs.expand(initFreeSize);
    }

    /**
     * 租借一个空闲对象
     * @return
     * @throws NoMoreFreeObj
     */
    public PoolObjNode<E> borrowObj() throws NoMoreFreeObj {
        try {
            adjustFreeListSize();
            PoolObjNode<E> node = freeObjs.removeTail();
            borrowedObjs.add(node);
            return node;
        } catch (NodePointerNull nodePointerNull) {
            nodePointerNull.printStackTrace();
            return null;
        } catch (NodeNotExist nodeNotExist) {
            throw new NoMoreFreeObj();
        }
    }

    /**
     * 调整空闲空间容量
     * 通过回调调整器获取要调整的数量
     */
    private void adjustFreeListSize(){
        int adjustSize = freeSizeAdjuster.adjust(freeObjs.size(),borrowedObjs.size());
        if (adjustSize == 0){
            return;
        }
        if (adjustSize > 0){
            freeObjs.expand(adjustSize);
        }else {
            freeObjs.reduce(adjustSize);
        }
    }

    /**
     * 归还空闲对象
     * @param obj
     * @throws NodePointerNull
     */
    public void returnObj(PoolObjNode<E> obj) throws NodePointerNull {
        adjustFreeListSize();
        PoolObjNode<E> node = borrowedObjs.remove(obj);
        freeObjs.add(node);
    }

    public int borrowedNums() {
        return borrowedObjs.size();
    }

    public int freeNums() {
        return freeObjs.size();
    }

    public void clearFreeObjs() {
        freeObjs.clear();
    }
}
