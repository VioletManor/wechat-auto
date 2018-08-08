package xyz.ipurple.wechat.base.core.sync;

import xyz.ipurple.wechat.base.core.init.BaseResponse;
import xyz.ipurple.wechat.base.core.sync.key.SyncCheckKeyEntity;
import xyz.ipurple.wechat.base.core.sync.key.SyncKeyEntity;
import xyz.ipurple.wechat.base.core.sync.msg.MsgEntity;
import xyz.ipurple.wechat.base.core.sync.profile.ProfileEntity;

import java.util.List;

/**
 * @ClassName: SyncEntity
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/8 9:51
 * @Version: 1.0
 */
public class SyncEntity {
    private int AddMsgCount;
    private List<MsgEntity> AddMsgList;
    private BaseResponse BaseResponse;
    private int ContinueFlag;
    private int DelContactCount;
    private List DelContactList;
    private int ModChatRoomMemberCount;
    private List ModChatRoomMemberList;
    private int ModContactCount;
    private List ModContactList;
    private ProfileEntity Profile;
    private String SKey;
    private SyncCheckKeyEntity SyncCheckKey;
    private SyncKeyEntity SyncKey;

    public int getAddMsgCount() {
        return AddMsgCount;
    }

    public void setAddMsgCount(int addMsgCount) {
        AddMsgCount = addMsgCount;
    }

    public List<MsgEntity> getAddMsgList() {
        return AddMsgList;
    }

    public void setAddMsgList(List<MsgEntity> addMsgList) {
        AddMsgList = addMsgList;
    }

    public xyz.ipurple.wechat.base.core.init.BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(xyz.ipurple.wechat.base.core.init.BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public int getContinueFlag() {
        return ContinueFlag;
    }

    public void setContinueFlag(int continueFlag) {
        ContinueFlag = continueFlag;
    }

    public int getDelContactCount() {
        return DelContactCount;
    }

    public void setDelContactCount(int delContactCount) {
        DelContactCount = delContactCount;
    }

    public List getDelContactList() {
        return DelContactList;
    }

    public void setDelContactList(List delContactList) {
        DelContactList = delContactList;
    }

    public int getModChatRoomMemberCount() {
        return ModChatRoomMemberCount;
    }

    public void setModChatRoomMemberCount(int modChatRoomMemberCount) {
        ModChatRoomMemberCount = modChatRoomMemberCount;
    }

    public List getModChatRoomMemberList() {
        return ModChatRoomMemberList;
    }

    public void setModChatRoomMemberList(List modChatRoomMemberList) {
        ModChatRoomMemberList = modChatRoomMemberList;
    }

    public int getModContactCount() {
        return ModContactCount;
    }

    public void setModContactCount(int modContactCount) {
        ModContactCount = modContactCount;
    }

    public List getModContactList() {
        return ModContactList;
    }

    public void setModContactList(List modContactList) {
        ModContactList = modContactList;
    }

    public ProfileEntity getProfile() {
        return Profile;
    }

    public void setProfile(ProfileEntity profile) {
        Profile = profile;
    }

    public String getSKey() {
        return SKey;
    }

    public void setSKey(String SKey) {
        this.SKey = SKey;
    }

    public SyncCheckKeyEntity getSyncCheckKey() {
        return SyncCheckKey;
    }

    public void setSyncCheckKey(SyncCheckKeyEntity syncCheckKey) {
        SyncCheckKey = syncCheckKey;
    }

    public SyncKeyEntity getSyncKey() {
        return SyncKey;
    }

    public void setSyncKey(SyncKeyEntity syncKey) {
        SyncKey = syncKey;
    }
}
