package com.celcsa.payroll.domain.schedule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.celcsa.payroll.domain.base.BaseEntity;
import com.celcsa.payroll.domain.employee.projects.WorkingProject;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Shift
 */
@Data
@NoArgsConstructor
@Builder
@Document(collection = "shift")
public class Shift extends BaseEntity{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final BigDecimal SIXTY = new BigDecimal(60);

   
    @Indexed
    private Date from;
    @Indexed
    private Date to;
    private String projectName;

    public Shift(Date from, Date to){
        this.from = from;
        this.to = to;
    }

    public Shift(Date from, Date to, String projectName){
       
        this.from = from;
        this.to = to;
        this.projectName = projectName;
    }
    
    public Duration getDuration(){
        if(from==null || to ==null)return Duration.between(Instant.now(), Instant.now());
        return Duration.between(from.toInstant(), to.toInstant());
    }

    public long getDurationInHours() {
        return getDuration().toHours();
    }

    public double getDurationsInHoursAndMinutes(){
        final BigDecimal minutes = new BigDecimal(getDuration().toMinutes());
        minutes.setScale(1, RoundingMode.HALF_UP);
        return minutes.divide(SIXTY).doubleValue();
    }

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime  from = LocalDateTime.parse("2014-11-25 08:00:00", formatter);

        LocalDateTime to= LocalDateTime.parse("2014-11-25 17:30:00", formatter);

        WorkingProject p = new WorkingProject();

        Date one = Date.from( from.atZone( ZoneId.systemDefault()).toInstant());

        Date two = Date.from( to.atZone( ZoneId.systemDefault()).toInstant());

        Shift s = new Shift(one, two);

        System.out.println(s.getDurationsInHoursAndMinutes());
    }

}