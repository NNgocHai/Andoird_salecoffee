package hcmute.edu.vn.mssv18110278.Entity;

import android.content.Context;

import java.util.List;

import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class Admin extends User {

  public Admin(int id, String username, String gmail, int role, byte[] hinh) {
    super(id, username, gmail, role, hinh);
  }

  public boolean promoteEmployee(Employee employee, Context context) {
    int userId = employee.getId();
    int roleId = -1;
    boolean complete;

    List<Integer> roleIds = DatabaseSelectHelper.getRoleIds(context);
    for (int id : roleIds) {
      if (DatabaseSelectHelper.getRoleName(id, context).equals((Roles.ADMIN.name()))) {
        roleId = id;
      }
    }

    complete = DatabaseUpdateHelper.updateUserRole(roleId, userId, context);
    return complete;
  }
}

