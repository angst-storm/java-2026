package ru.kiprin.demo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "uid не может быть пустым")
    @Size(max = 32, message = "uid должен содержать не более 32 символов")
    private String uid;

    @NotBlank(message = "operationUid не может быть пустым")
    @Size(max = 32, message = "operationUid должен содержать не более 32 символов")
    private String operationUid;

    private Systems systemName;

    @NotBlank(message = "systemTime не может быть пустым")
    private String systemTime;

    private String source;

    private Positions position;

    private Double salary;

    private Double bonus;

    private Integer workDays;

    @Min(value = 1, message = "communicationId должен быть не меньше 1")
    @Max(value = 100000, message = "communicationId должен быть не больше 100000")
    private Integer communicationId;

    private Integer templateId;
    private Integer productCode;
    private Integer smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position=" + position +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}
