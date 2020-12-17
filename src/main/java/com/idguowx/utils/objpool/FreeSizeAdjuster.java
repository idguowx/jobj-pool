package com.idguowx.utils.objpool;

/**
 * 空闲队列大小调整器，每次borrow或return时被回调
 */
public interface FreeSizeAdjuster {
    /**
     * 计算要调整的数量
     * @param curFreeSize 当前空闲队列数量
     * @param curBorrowedSize 当前借出对象数量
     * @return 要调整的数量，小于0时缩容，大于0时扩容，等于0时不做操作
     */
    int adjust(int curFreeSize , int curBorrowedSize);
}
