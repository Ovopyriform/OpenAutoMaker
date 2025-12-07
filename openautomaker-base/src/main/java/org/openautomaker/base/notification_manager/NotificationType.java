package org.openautomaker.base.notification_manager;

/**
 *
 * @author ianhudson
 */
public enum NotificationType
{

    NOTE(0),
    WARNING(1),
    CAUTION(2);

    private final int value;

    private NotificationType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
