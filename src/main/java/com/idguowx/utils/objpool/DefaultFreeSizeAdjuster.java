package com.idguowx.utils.objpool;

/**
 * 默认空闲队列调整器
 */
public class DefaultFreeSizeAdjuster implements FreeSizeAdjuster {
    public int adjust(int curFreeSize, int curBorrowedSize) {
        int reduceNum = reduceNum(curFreeSize,curBorrowedSize);
        if (reduceNum != 0){
            return 0-reduceNum;
        }
        return expandNum(curFreeSize,curBorrowedSize);
    }

    /**
     * 计算需要缩容的数量,默认不做缩容操作
     * @param curFreeSize
     * @param curBorrowedSize
     * @return
     */
    private int reduceNum(int curFreeSize, int curBorrowedSize){
        return 0;
    }

    /**
     * 扩充空闲数量，当空闲少于10，扩充1000
     * @param curFreeSize
     * @param curBorrowedSize
     * @return
     */
    private int expandNum(int curFreeSize, int curBorrowedSize){
        if (curFreeSize < 10 ){
            return 1000;
        }
        return 0;
    }
}
