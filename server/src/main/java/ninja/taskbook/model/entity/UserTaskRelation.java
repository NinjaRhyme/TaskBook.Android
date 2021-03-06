package ninja.taskbook.model.entity;

//----------------------------------------------------------------------------------------------------
public class UserTaskRelation {

    //----------------------------------------------------------------------------------------------------
    public enum UserTaskRole {
        USER_TASK_ADMIN,
        USER_TASK_MEMBER,
    }

    //----------------------------------------------------------------------------------------------------
    public int userTaskId;
    public int userId;
    public int taskId;
    public int userRole;

    //----------------------------------------------------------------------------------------------------
    public UserTaskRelation() {

    }

    //----------------------------------------------------------------------------------------------------
    public UserTaskRelation(int _userTaskId, int _userId, int _taskId, int _userRole) {
        userTaskId = _userTaskId;
        userId = _userId;
        taskId = _taskId;
        userRole = _userRole;
    }
}
