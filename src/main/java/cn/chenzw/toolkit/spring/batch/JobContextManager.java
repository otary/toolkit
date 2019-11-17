package cn.chenzw.toolkit.spring.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.JobContext;
import org.springframework.batch.core.scope.context.JobSynchronizationManager;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;

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

        if (context == null) {
            throw new NullPointerException("StepContext is null.");
        }

        return context.getStepExecution();
    }


    /**
     * 获取JobExecution
     *
     * @return
     */
    public static JobExecution getJobExecution() {
        JobContext context = JobSynchronizationManager.getContext();

        if (context == null) {
            throw new NullPointerException("JobContext is null.");
        }
        return context.getJobExecution();
    }
}
