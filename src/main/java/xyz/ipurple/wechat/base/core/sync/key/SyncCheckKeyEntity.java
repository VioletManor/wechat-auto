package xyz.ipurple.wechat.base.core.sync.key;

import java.util.List;

/**
 * @ClassName: SyncCheckKeyEntity
 * @Description:
 * @Author: zcy
 * @Date: 2018/8/8 9:54
 * @Version: 1.0
 */
public class SyncCheckKeyEntity {
    private int Count;
    private List<KeyEntity> List;

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public java.util.List<KeyEntity> getList() {
        return List;
    }

    public void setList(java.util.List<KeyEntity> list) {
        List = list;
    }
}
