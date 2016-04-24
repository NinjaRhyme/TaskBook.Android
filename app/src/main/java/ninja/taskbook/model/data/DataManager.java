package ninja.taskbook.model.data;

import java.util.List;

import ninja.taskbook.model.entity.GroupEntity;
import ninja.taskbook.model.entity.UserEntity;

//----------------------------------------------------------------------------------------------------
public class DataManager {
    //----------------------------------------------------------------------------------------------------
    private static DataManager sInstance = null;

    //----------------------------------------------------------------------------------------------------
    private UserEntity mUserInfo;
    private List<GroupEntity> mGroupInfos;

    //----------------------------------------------------------------------------------------------------
    private DataManager() {

    }

    //----------------------------------------------------------------------------------------------------
    public static DataManager getInstance() {
        synchronized (DataManager.class) {
            if (sInstance == null) {
                sInstance = new DataManager();
            }
        }
        return sInstance;
    }

    //----------------------------------------------------------------------------------------------------
    public UserEntity getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserEntity userInfo) {
        this.mUserInfo = userInfo;
    }

    public List<GroupEntity> getGroupInfos() {
        return mGroupInfos;
    }

    public void setGroupInfos(List<GroupEntity> groupInfos) {
        this.mGroupInfos = groupInfos;
    }
}