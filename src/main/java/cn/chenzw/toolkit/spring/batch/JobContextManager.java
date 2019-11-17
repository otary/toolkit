package cn.chenzw.toolkit.spring.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.JobContext;
import org.springframework.batch.core.scope.context.JobSynchronizationManager;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;

import java.util.Objects;

/**
 * 批处理Job容器
 *
 * @author chenzw
 */
public final class JobContextManager {

    private JobContextManager() {
    }

    /**
     * 获取StepExecution
     *
     * @return
     */
    public static StepExecution getStepExecution() {
        StepContext context = StepSynchronizationManager.getContext();
        Objects.requireNonNull(context, "StepContext is null.");
        return context.getStepExecution();
    }


    /**
     * 获取JobExecution
     *
     * @return
     */
    public static JobExecution getJobExecution() {
        JobContext context = JobSynchronizationManager.getContext();
        Objects.requireNonNull(context, "JobContext is null.");
        return context.getJobExecution();
    }
}
