
package org.openautomaker.base.task_executor;

/**
 *
 * @author tony
 * @param <T>
 */
public interface TaskResponder<T>
{
    public void taskEnded(TaskResponse<T> taskResponse);
}
