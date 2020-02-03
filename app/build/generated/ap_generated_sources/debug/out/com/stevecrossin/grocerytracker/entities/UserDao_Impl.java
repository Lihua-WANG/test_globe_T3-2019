package com.stevecrossin.grocerytracker.entities;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser_1;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLoginStatus;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user` (`userID`,`user_name`,`email`,`pass_key`,`age`,`height`,`weight`,`user_gender`,`postcode`,`householdMembers`,`householdAdults`,`householdChildren`,`login_status`,`shop_number`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getUserID());
        if (value.getUserName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUserName());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEmail());
        }
        if (value.getPassKey() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPassKey());
        }
        if (value.getUserAge() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUserAge());
        }
        if (value.getUserHeight() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUserHeight());
        }
        if (value.getUserWeight() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUserWeight());
        }
        if (value.getUserGender() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUserGender());
        }
        if (value.getPostCode() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getPostCode());
        }
        if (value.getHouseholdMembers() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getHouseholdMembers());
        }
        if (value.getHouseHoldAdults() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getHouseHoldAdults());
        }
        if (value.getHouseHoldChildren() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getHouseHoldChildren());
        }
        final int _tmp;
        _tmp = value.isLoggedIn() ? 1 : 0;
        stmt.bindLong(13, _tmp);
        if (value.getShopNumber() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getShopNumber());
        }
      }
    };
    this.__insertionAdapterOfUser_1 = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR FAIL INTO `user` (`userID`,`user_name`,`email`,`pass_key`,`age`,`height`,`weight`,`user_gender`,`postcode`,`householdMembers`,`householdAdults`,`householdChildren`,`login_status`,`shop_number`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getUserID());
        if (value.getUserName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUserName());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEmail());
        }
        if (value.getPassKey() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPassKey());
        }
        if (value.getUserAge() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUserAge());
        }
        if (value.getUserHeight() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUserHeight());
        }
        if (value.getUserWeight() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUserWeight());
        }
        if (value.getUserGender() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUserGender());
        }
        if (value.getPostCode() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getPostCode());
        }
        if (value.getHouseholdMembers() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getHouseholdMembers());
        }
        if (value.getHouseHoldAdults() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getHouseHoldAdults());
        }
        if (value.getHouseHoldChildren() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getHouseHoldChildren());
        }
        final int _tmp;
        _tmp = value.isLoggedIn() ? 1 : 0;
        stmt.bindLong(13, _tmp);
        if (value.getShopNumber() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getShopNumber());
        }
      }
    };
    this.__preparedStmtOfUpdateLoginStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user SET login_status = ?  WHERE userID = ?;";
        return _query;
      }
    };
  }

  @Override
  public void insertUser(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertUserFromLogin(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser_1.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateLoginStatus(final int userId, final boolean isLogin) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLoginStatus.acquire();
    int _argIndex = 1;
    final int _tmp;
    _tmp = isLogin ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, userId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateLoginStatus.release(_stmt);
    }
  }

  @Override
  public List<User> getAllUsers() {
    final String _sql = "SELECT * FROM user;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(_cursor, "userID");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPassKey = CursorUtil.getColumnIndexOrThrow(_cursor, "pass_key");
      final int _cursorIndexOfUserAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfUserHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
      final int _cursorIndexOfUserWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfUserGender = CursorUtil.getColumnIndexOrThrow(_cursor, "user_gender");
      final int _cursorIndexOfPostCode = CursorUtil.getColumnIndexOrThrow(_cursor, "postcode");
      final int _cursorIndexOfHouseholdMembers = CursorUtil.getColumnIndexOrThrow(_cursor, "householdMembers");
      final int _cursorIndexOfHouseHoldAdults = CursorUtil.getColumnIndexOrThrow(_cursor, "householdAdults");
      final int _cursorIndexOfHouseHoldChildren = CursorUtil.getColumnIndexOrThrow(_cursor, "householdChildren");
      final int _cursorIndexOfIsLoggedIn = CursorUtil.getColumnIndexOrThrow(_cursor, "login_status");
      final int _cursorIndexOfShopNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "shop_number");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpPassKey;
        _tmpPassKey = _cursor.getString(_cursorIndexOfPassKey);
        final String _tmpUserAge;
        _tmpUserAge = _cursor.getString(_cursorIndexOfUserAge);
        final String _tmpUserHeight;
        _tmpUserHeight = _cursor.getString(_cursorIndexOfUserHeight);
        final String _tmpUserWeight;
        _tmpUserWeight = _cursor.getString(_cursorIndexOfUserWeight);
        final String _tmpUserGender;
        _tmpUserGender = _cursor.getString(_cursorIndexOfUserGender);
        final String _tmpPostCode;
        _tmpPostCode = _cursor.getString(_cursorIndexOfPostCode);
        final String _tmpHouseholdMembers;
        _tmpHouseholdMembers = _cursor.getString(_cursorIndexOfHouseholdMembers);
        final String _tmpHouseHoldAdults;
        _tmpHouseHoldAdults = _cursor.getString(_cursorIndexOfHouseHoldAdults);
        final String _tmpHouseHoldChildren;
        _tmpHouseHoldChildren = _cursor.getString(_cursorIndexOfHouseHoldChildren);
        final String _tmpShopNumber;
        _tmpShopNumber = _cursor.getString(_cursorIndexOfShopNumber);
        _item = new User(_tmpUserName,_tmpEmail,_tmpPassKey,_tmpUserAge,_tmpUserHeight,_tmpUserWeight,_tmpUserGender,_tmpPostCode,_tmpHouseholdMembers,_tmpHouseHoldAdults,_tmpHouseHoldChildren,_tmpShopNumber);
        final int _tmpUserID;
        _tmpUserID = _cursor.getInt(_cursorIndexOfUserID);
        _item.setUserID(_tmpUserID);
        final boolean _tmpIsLoggedIn;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsLoggedIn);
        _tmpIsLoggedIn = _tmp != 0;
        _item.setLoggedIn(_tmpIsLoggedIn);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getSignInUser() {
    final String _sql = "SELECT * from user WHERE login_status= 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(_cursor, "userID");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPassKey = CursorUtil.getColumnIndexOrThrow(_cursor, "pass_key");
      final int _cursorIndexOfUserAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfUserHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
      final int _cursorIndexOfUserWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfUserGender = CursorUtil.getColumnIndexOrThrow(_cursor, "user_gender");
      final int _cursorIndexOfPostCode = CursorUtil.getColumnIndexOrThrow(_cursor, "postcode");
      final int _cursorIndexOfHouseholdMembers = CursorUtil.getColumnIndexOrThrow(_cursor, "householdMembers");
      final int _cursorIndexOfHouseHoldAdults = CursorUtil.getColumnIndexOrThrow(_cursor, "householdAdults");
      final int _cursorIndexOfHouseHoldChildren = CursorUtil.getColumnIndexOrThrow(_cursor, "householdChildren");
      final int _cursorIndexOfIsLoggedIn = CursorUtil.getColumnIndexOrThrow(_cursor, "login_status");
      final int _cursorIndexOfShopNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "shop_number");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpPassKey;
        _tmpPassKey = _cursor.getString(_cursorIndexOfPassKey);
        final String _tmpUserAge;
        _tmpUserAge = _cursor.getString(_cursorIndexOfUserAge);
        final String _tmpUserHeight;
        _tmpUserHeight = _cursor.getString(_cursorIndexOfUserHeight);
        final String _tmpUserWeight;
        _tmpUserWeight = _cursor.getString(_cursorIndexOfUserWeight);
        final String _tmpUserGender;
        _tmpUserGender = _cursor.getString(_cursorIndexOfUserGender);
        final String _tmpPostCode;
        _tmpPostCode = _cursor.getString(_cursorIndexOfPostCode);
        final String _tmpHouseholdMembers;
        _tmpHouseholdMembers = _cursor.getString(_cursorIndexOfHouseholdMembers);
        final String _tmpHouseHoldAdults;
        _tmpHouseHoldAdults = _cursor.getString(_cursorIndexOfHouseHoldAdults);
        final String _tmpHouseHoldChildren;
        _tmpHouseHoldChildren = _cursor.getString(_cursorIndexOfHouseHoldChildren);
        final String _tmpShopNumber;
        _tmpShopNumber = _cursor.getString(_cursorIndexOfShopNumber);
        _result = new User(_tmpUserName,_tmpEmail,_tmpPassKey,_tmpUserAge,_tmpUserHeight,_tmpUserWeight,_tmpUserGender,_tmpPostCode,_tmpHouseholdMembers,_tmpHouseHoldAdults,_tmpHouseHoldChildren,_tmpShopNumber);
        final int _tmpUserID;
        _tmpUserID = _cursor.getInt(_cursorIndexOfUserID);
        _result.setUserID(_tmpUserID);
        final boolean _tmpIsLoggedIn;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsLoggedIn);
        _tmpIsLoggedIn = _tmp != 0;
        _result.setLoggedIn(_tmpIsLoggedIn);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUser(final String userName) {
    final String _sql = "SELECT * from user WHERE user_name=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(_cursor, "userID");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPassKey = CursorUtil.getColumnIndexOrThrow(_cursor, "pass_key");
      final int _cursorIndexOfUserAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfUserHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
      final int _cursorIndexOfUserWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfUserGender = CursorUtil.getColumnIndexOrThrow(_cursor, "user_gender");
      final int _cursorIndexOfPostCode = CursorUtil.getColumnIndexOrThrow(_cursor, "postcode");
      final int _cursorIndexOfHouseholdMembers = CursorUtil.getColumnIndexOrThrow(_cursor, "householdMembers");
      final int _cursorIndexOfHouseHoldAdults = CursorUtil.getColumnIndexOrThrow(_cursor, "householdAdults");
      final int _cursorIndexOfHouseHoldChildren = CursorUtil.getColumnIndexOrThrow(_cursor, "householdChildren");
      final int _cursorIndexOfIsLoggedIn = CursorUtil.getColumnIndexOrThrow(_cursor, "login_status");
      final int _cursorIndexOfShopNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "shop_number");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpPassKey;
        _tmpPassKey = _cursor.getString(_cursorIndexOfPassKey);
        final String _tmpUserAge;
        _tmpUserAge = _cursor.getString(_cursorIndexOfUserAge);
        final String _tmpUserHeight;
        _tmpUserHeight = _cursor.getString(_cursorIndexOfUserHeight);
        final String _tmpUserWeight;
        _tmpUserWeight = _cursor.getString(_cursorIndexOfUserWeight);
        final String _tmpUserGender;
        _tmpUserGender = _cursor.getString(_cursorIndexOfUserGender);
        final String _tmpPostCode;
        _tmpPostCode = _cursor.getString(_cursorIndexOfPostCode);
        final String _tmpHouseholdMembers;
        _tmpHouseholdMembers = _cursor.getString(_cursorIndexOfHouseholdMembers);
        final String _tmpHouseHoldAdults;
        _tmpHouseHoldAdults = _cursor.getString(_cursorIndexOfHouseHoldAdults);
        final String _tmpHouseHoldChildren;
        _tmpHouseHoldChildren = _cursor.getString(_cursorIndexOfHouseHoldChildren);
        final String _tmpShopNumber;
        _tmpShopNumber = _cursor.getString(_cursorIndexOfShopNumber);
        _result = new User(_tmpUserName,_tmpEmail,_tmpPassKey,_tmpUserAge,_tmpUserHeight,_tmpUserWeight,_tmpUserGender,_tmpPostCode,_tmpHouseholdMembers,_tmpHouseHoldAdults,_tmpHouseHoldChildren,_tmpShopNumber);
        final int _tmpUserID;
        _tmpUserID = _cursor.getInt(_cursorIndexOfUserID);
        _result.setUserID(_tmpUserID);
        final boolean _tmpIsLoggedIn;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsLoggedIn);
        _tmpIsLoggedIn = _tmp != 0;
        _result.setLoggedIn(_tmpIsLoggedIn);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
