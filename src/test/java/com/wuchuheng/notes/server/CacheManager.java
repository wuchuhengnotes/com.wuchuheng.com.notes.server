/**
 * Desc    The controllers is part of server
 * Author  wuchuheng <root@wuchuheng.com>
 * Blog    https://wuchuheng.com
 * DATE    2022/1/4
 * Listen  MIT
 */

package com.wuchuheng.notes.server;

public class CacheManager {
    private static Long newTodoRecordId;

    public static void setNewTodoRecordId(Long id){
        newTodoRecordId = id;
    }

    public static Long getNewTodoRecordId(){
        return newTodoRecordId;
    }
}
