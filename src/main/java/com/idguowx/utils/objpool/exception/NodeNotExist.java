package com.idguowx.utils.objpool.exception;

/**
 * 节点不存在
 */
public class NodeNotExist extends Exception {
    public NodeNotExist(){
    }
    public NodeNotExist(String msg){
        super(msg);
    }
}
