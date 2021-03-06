/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package ninja.taskbook.model.network.thrift.service;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum ThriftNotificationType implements org.apache.thrift.TEnum {
  NOTIFICATION_JOIN(0),
  NOTIFICATION_JOIN_ANSWER(1),
  NOTIFICATION_INVITE(2),
  NOTIFICATION_INVITE_ANSWER(3),
  NOTIFICATION_ALERT(4);

  private final int value;

  private ThriftNotificationType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ThriftNotificationType findByValue(int value) { 
    switch (value) {
      case 0:
        return NOTIFICATION_JOIN;
      case 1:
        return NOTIFICATION_JOIN_ANSWER;
      case 2:
        return NOTIFICATION_INVITE;
      case 3:
        return NOTIFICATION_INVITE_ANSWER;
      case 4:
        return NOTIFICATION_ALERT;
      default:
        return null;
    }
  }
}
