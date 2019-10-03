package com.celcsa.payroll.domain.schedule.bl;

import java.time.LocalDateTime;
import java.util.List;

import com.celcsa.payroll.domain.schedule.Shift;
import com.celcsa.payroll.util.DateUtil;

import lombok.Synchronized;

/**
 * ValidateShift
 */
public class ValidateShift {

    @Synchronized
    public static boolean isShiftConfilct(final Shift shift, final List<Shift> foundShifts){

        final LocalDateTime addShiftFrom = DateUtil.convertToLocalDateViaInstant(shift.getFrom());
        final LocalDateTime addShiftTo =  DateUtil.convertToLocalDateViaInstant(shift.getTo());

        for(Shift s:foundShifts){

            final LocalDateTime currentShiftFrom = DateUtil.convertToLocalDateViaInstant((s.getFrom()));
            final LocalDateTime currentShiftTo = DateUtil.convertToLocalDateViaInstant((s.getTo()));

            final boolean isAddShiftFromInBetweenCurrentShift = addShiftFrom.isAfter(currentShiftFrom) || addShiftFrom.isBefore(currentShiftTo);
            final boolean isAddShiftToInBetweenCurrentShift = addShiftTo.isAfter(currentShiftTo) || addShiftTo.isBefore(currentShiftTo);

            if(isAddShiftFromInBetweenCurrentShift || isAddShiftToInBetweenCurrentShift)
                return true;

        }
        return false;
    }
    
}