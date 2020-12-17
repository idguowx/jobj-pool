package com.idguowx.utils;

import com.idguowx.utils.objpool.ObjPool;
import com.idguowx.utils.objpool.PoolObjNode;
import com.idguowx.utils.objpool.PoolFactory;
import org.junit.Test;


public class LinkedObjPoolTest {
    @org.junit.Test
    public void borrowObj() throws Exception {
        ObjPool<PoolObj> pool = PoolFactory.getDefaultObjPool(100,PoolObj.class);
        int bn = pool.borrowedNums();
        int fn = pool.freeNums();
        PoolObjNode<PoolObj> n =  pool.borrowObj();
        n =  pool.borrowObj();
        int bn1 = pool.borrowedNums();
        int fn1 = pool.freeNums();

        if (bn1 - bn != 2 || fn - fn1 != 2){
            throw new Exception();
        }
    }

    @org.junit.Test
    public void returnObj() throws Exception {
        ObjPool<PoolObj> pool = PoolFactory.getDefaultObjPool(100,PoolObj.class);
               int bn = pool.borrowedNums();
        int fn = pool.freeNums();
        PoolObjNode<PoolObj> n =  pool.borrowObj();
        PoolObjNode<PoolObj> n1 =  pool.borrowObj();
        int bn1 = pool.borrowedNums();
         int fn1 = pool.freeNums();

        if (bn1 - bn != 2 || fn - fn1 != 2){
            throw new Exception();
        }

         n.getItem().setName("n");
        n1.getItem().setName("n1");

        bn = pool.borrowedNums();
        fn = pool.freeNums();
        pool.returnObj(n);
        bn1 = pool.borrowedNums();
        fn1 = pool.freeNums();

        if (bn - bn1 != 1 || fn1 - fn != 1){
            throw new Exception();
        }
    }

    @org.junit.Test
    public void freeSizeExpand() throws Exception {
        ObjPool<PoolObj> pool = PoolFactory.getDefaultObjPool(100,PoolObj.class);

        int bn = pool.borrowedNums();
        int fn = pool.freeNums();
        for (int i = 0 ; i < 100 ; i++){
            pool.borrowObj();
        }
        int bn1 = pool.borrowedNums();
        int fn1 = pool.freeNums();

        if (bn1 - bn != 100 || fn1 != 1000){
            throw new Exception();
        }
    }

    @org.junit.Test
    public void clearFreeObjs() throws Exception {
        ObjPool<PoolObj> pool = PoolFactory.getDefaultObjPool(100,PoolObj.class);
        int fn = pool.freeNums();
        pool.clearFreeObjs();
        int fn1 = pool.freeNums();

        if (fn1 != 0){
            throw new Exception();
        }
    }

    @Test
    public void testSpeed() throws Exception {
        ObjPool<PoolObj> pool = PoolFactory.getDefaultObjPool(100,PoolObj.class);
        long before = System.currentTimeMillis();
        for (int i = 0 ; i < 1000000 ; i++){
            PoolObjNode<PoolObj> n = pool.borrowObj();
            pool.returnObj(n);
        }

        long after = System.currentTimeMillis();
        System.out.println("1000000个borrow+return操作耗时："+(after-before));
    }
}