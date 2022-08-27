package vn.fis.logfile.vinasoy.dto.SFLASH;

import java.time.LocalDateTime;

public class Task_SFlash_Dto {

    private Long idTask;
    private String taskName;
    private LocalDateTime taskCreatedAt;
    private String size;

    public Task_SFlash_Dto(Long idTask, String taskName, LocalDateTime taskCreatedAt) {
        this.idTask = idTask;
        this.taskName = taskName;
        this.taskCreatedAt = taskCreatedAt;
    }

    public Task_SFlash_Dto(Long idTask, String taskName, LocalDateTime taskCreatedAt, String size) {
        this.idTask = idTask;
        this.taskName = taskName;
        this.taskCreatedAt = taskCreatedAt;
        this.size = size;
    }

    public Task_SFlash_Dto() {
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getTaskCreatedAt() {
        return taskCreatedAt;
    }

    public void setTaskCreatedAt(LocalDateTime taskCreatedAt) {
        this.taskCreatedAt = taskCreatedAt;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
