package xyz.ipurple.wechat.base.core;

/**
 * @author: zcy
 * @Description:
 * @Date: 2018/8/6 20:44
 * @Modified By:
 */
public class BaseResponse {
    private String ErrMsg;
    private int Ret;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public int getRet() {
        return Ret;
    }

    public void setRet(int ret) {
        Ret = ret;
    }
}
