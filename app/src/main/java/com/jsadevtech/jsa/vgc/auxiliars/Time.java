package com.jsadevtech.vgc.servicetest;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jaquer on 16/06/2015. Modified by Shirkam on 06/09/2015
 * Clase encargada de llevar fechas hasta minutos de precision. Util para llevar los
 * tiempos de los eventos.
 */
public class Time {
    /**
     * El numero maximo que un minuto puede tener, el minimo es 0.
     */
    public static final int MAX_MIN = 59;
    /**
     * El numero maximo que una hora puede tener, el minimo es 0.
     */
    public static final int MAX_HOUR = 23;
    /**
     * Number of minutes in a hour.
     */
    public static final int MINUTES_IN_A_HOUR = 60;
    /**
     * Number of minutes in a day.
     */
    public static final int MINUTES_IN_A_DAY = 1440;
    /**
     * Number of hours in a day.
     */
    public static final int HOURS_IN_A_DAY = 24;
    /**
     * Number of days in a standar month.
     */
    public static final int DAYS_IN_A_MONTH = 30;
    /**
     * Number of days in a standar year.
     */
    public static final int DAYS_IN_A_YEAR = 365;
    /**
     * Number of months in a year.
     */
    public static final int MONTHS_IN_A_YEAR = 12;

    /**
     * The day of the date.
     */
    private int day;
    /**
     * The month of the date.
     */
    private int month;
    /**
     * The year of the date.
     */
    private int year;
    /**
     * The hour of the date.
     */
    private int hour;
    /**
     * The minute of the date.
     */
    private int min;

    /**
     * Default constructor, set the date to 190/01/01 00:00.
     */
    public Time() {
        this.day = 1;
        this.month = 1;
        this.year = 1970;
        this.hour = 0;
        this.min = 0;
    }

    /**
     * Constructor, if the args are a valid date, sets the fields to them, otherwise,
     * the date is set to 1970/01/01 00:00. With this constructor, the hour is set to 0.
     * same with min.
     * @param d The day.
     * @param m The month.
     * @param y The year.
     */
    public Time(int d, int m, int y) {
        if(isFechaValida(d, m, y)) {
            this.day = d;
            this.month = m;
            this.year = y;
            this.hour = 0;
            this.min = 0;
        }
        else {
            this.day = 1;
            this.month = 1;
            this.year = 1970;
            this.hour = 0;
            this.min = 0;
        }

    }

    /**
     * Constructor, if the args are a valid date, sets the fields to them, otherwise,
     * the date is set to 1970/01/01 00:00.
     * same with min.
     * @param d The day.
     * @param m The month.
     * @param y The year.
     */
    public Time(int d, int m, int y, int h, int min) {
        if(isFechaValida(d, m, y) && isHoraValida(h, min)) {
            this.day = d;
            this.month = m;
            this.year = y;
            this.hour = h;
            this.min = min;
        }
        else {
            this.day = 1;
            this.month = 1;
            this.year = 1970;
            this.hour = 0;
            this.min = 0;
        }
    }

    /**
     * Adds the minutes amount of the arg to the current date.
     * @param min The number of minutes to add.
     */
    public void add(int min) {

        if(min > 0) {
            while(min > 0) {
                if(this.hour == MAX_HOUR) {
                    if(min+this.min > MAX_MIN) {
                        min -= (MAX_MIN-this.min)+1;
                        this.min = 0;
                        this.hour = 0;
                        sumarDias(1); //Sumamos un dia;
                    }
                    else
                    {
                        this.min += min;
                        min = 0;
                    }
                }
                else {
                    if(min+this.min > MAX_MIN) {
                        //diaSum -= (diasFeb-t.day)+1;
                        min -= (MAX_MIN-this.min)+1;
                        this.min = 0;
                        this.hour++;
                    }
                    else
                    {
                        this.min += min;
                        min = 0;
                    }
                }
            }
        }
    }

    /**
     * Adds a quantity to a field, determined by the first parameter. Minutes(1), hours(2), days(3),
     * months(4), years(5). If the arg is not one of these, default field is minutes(1).
     * Months added are supossed to have 30 days. Years added are suposed to have 365 days.
     * @param field The field where you are adding a quantity.
     * @param c The quantity to add.
     */
    public void add(int field, int c) {
        int days, min;
        switch(field) {
            case 1:
                days = c/MINUTES_IN_A_DAY;
                min = c%MINUTES_IN_A_DAY;
                sumarDias(days);
                add(min);
                break;
            case 2:
                days = c/HOURS_IN_A_DAY;
                min = (c%HOURS_IN_A_DAY)*60;
                sumarDias(days);
                add(min);
                break;
            case 3:
                sumarDias(c);
                break;
            case 4:
                sumarDias(c * DAYS_IN_A_MONTH);
                break;
            case 5:
                sumarDias(c * DAYS_IN_A_YEAR);
                break;
            default:
                days = c/MINUTES_IN_A_DAY;
                min = c%MINUTES_IN_A_DAY;
                sumarDias(days);
                add(min);
        }
    }

    /**
     * Subtracts the minutes amount of the arg to the current date.
     * @param min The number of minutes to sub.
     * @throws TooManyMinutesException Thrown if the number of minutes in the arg is too large
     * to be subtracted to the date.
     */
    public void sub(int min) throws TooManyMinutesException{
        if(min > 0)
        {
            while(min > 0) {
                if(this.hour == 0) {
                    if((this.min-min) < 0) {
                        min -= this.min+1;
                        this.min = MAX_MIN;
                        this.hour = MAX_HOUR;
                        try {
                            restarDias(1);
                        } catch (TooManyDaysException ex)
                        { throw new TooManyMinutesException(); }
                    }
                    else {
                        this.min -= min;
                        min = 0;
                    }
                }
                else {
                    if((this.min-min) < 0) {
                        min -= this.min+1;
                        this.min = MAX_MIN;
                        this.hour--;
                    }
                    else {
                        this.min -= min;
                        min = 0;
                    }
                }
            }
        }
    }

    /**
     * Subtracts a quantity to a field, determined by the first parameter. Minutes(1), hours(2), days(3),
     * months(4), years(5). If the number of field is not one of these, default field is minutes(1).
     * Months subtracted are suposed to have 30 days. Years subtracted are suposed to have 365 days.
     * @param field The field where you are adding a quantity.
     * @param c The quantity to add.
     */
    public void sub(int field, int c) throws TooManyMinutesException, TooManyDaysException {
        int days, min;
        switch(field) {
            case 1:
                days = c/MINUTES_IN_A_DAY;
                min = c%MINUTES_IN_A_DAY;
                try {
                    restarDias(days);
                }catch(TooManyDaysException ex)
                { throw new TooManyMinutesException(); }
                sub(min);
                break;
            case 2:
                days = c/HOURS_IN_A_DAY;
                min = (c%HOURS_IN_A_DAY)*60;
                try {
                    restarDias(days);
                } catch(TooManyDaysException ex)
                { throw new TooManyMinutesException(); }
                sub(min);
                break;
            case 3:
                restarDias(c);
                break;
            case 4:
                restarDias(c * DAYS_IN_A_MONTH);
                break;
            case 5:
                restarDias(c * DAYS_IN_A_YEAR);
                break;
            default:
                days = c/MINUTES_IN_A_DAY;
                min = c%MINUTES_IN_A_DAY;
                try {
                    restarDias(days);
                }catch(TooManyDaysException ex)
                { throw new TooManyMinutesException(); }
                sub(min);
        }
    }

    /**
     * Calculates the time in minutes between two dates.
     * @param t The date where you sub.
     * @return The amount of minutes between the two dates.
     * @throws InvalidParameterException When the date in the arg is less than
     * the date calling the method.
     */
    public int sub(Time t) {
        int minutes = 0;

        if(!isFechaMayor(t))
            throw new InvalidParameterException("ERROR: La fecha pasada no puede ser menor.");

        minutes += (t.year - this.year) * MINUTES_IN_A_DAY * DAYS_IN_A_YEAR;
       if((t.month - this.month)  < 0)
           minutes += (MONTHS_IN_A_YEAR + (t.month - this.month)) * MINUTES_IN_A_DAY * DAYS_IN_A_MONTH;
       else
           minutes += (t.month - this.month) * MINUTES_IN_A_DAY * DAYS_IN_A_MONTH;
        if((t.day - this.day) < 0)
            minutes += (DAYS_IN_A_MONTH + (t.day - this.day)) * MINUTES_IN_A_DAY;
        else
            minutes += (t.day - this.day) * MINUTES_IN_A_DAY;
        if((t.hour - this.hour) < 0)
            minutes += (HOURS_IN_A_DAY + (t.hour - this.hour)) * MINUTES_IN_A_HOUR;
        else
            minutes += (t.hour - this.hour) * MINUTES_IN_A_HOUR;
        if((t.min - this.min) < 0)
            minutes += (MINUTES_IN_A_HOUR + (t.min - this.min));
        else
            minutes += (t.min - this.min);


        return minutes;
    }

    /**
     * @return Return the current date with 'yyyy-MM-dd HH:mm:ss' format.
     */
    public static String fechaHoraActual() {
        return new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    /**
     * Evaluates if an object is equal to the current date.
     * @param o The object to be evaluated
     * @return True if equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Time time = (Time) o;

        if (day != time.day) return false;
        if (month != time.month) return false;
        if (year != time.year) return false;
        if (hour != time.hour) return false;
        return min == time.min;

    }

    /**
     * Calculates an unique code for the current date.
     * @return The unique code.
     */
    @Override
    public int hashCode() {
        int result = day;
        result = DAYS_IN_A_MONTH * result + month;
        result = DAYS_IN_A_YEAR * result + year;
        result = HOURS_IN_A_DAY * result + hour;
        result = MINUTES_IN_A_DAY * result + min;
        return result;
    }

    /**
     * @return Returns a string reresentation of the date.
     */
    public String toString()
    { return this.year+"/"+this.month+"/"+this.day+" "+this.hour+":"+this.min;}

    public int getDay()
    { return day; }

    public int getMonth()
    { return month; }

    public int getYear()
    { return year; }

    public int getHour()
    { return hour; }

    public int getMin()
    { return min; }

    /**
     * Modifies the date if it is valid.
     * @param d The day to set.
     * @param m The month to set.
     * @param y The year to set.
     * @throws IllegalArgumentException if the args aren't valid.
     */
    public void modFecha(int d, int m, int y) {
        if(isFechaValida(d, m, y)) {
            this.day = d;
            this.month = m;
            this.year = y;
        }
        else
            throw new IllegalArgumentException("ERROR: La fecha pasada no es correcta.\n");
    }

    /**
     * Modifies the date if it is valid.
     * @param d The day to set.
     * @param m The month to set.
     * @param y The year to set.
     * @param h The hour to be set.
     * @param min The min to be set.
     * @throws IllegalArgumentException if the args aren't valid.
     */
    public void modFecha(int d, int m, int y, int h, int min) {
        if(isFechaValida(d, m, y) && isHoraValida(h, m)) {
            this.day = d;
            this.month = m;
            this.year = y;
            this.hour = h;
            this.min = min;
        }
        else
            throw new IllegalArgumentException("ERROR: La fecha pasada no es correcta.\n");
    }

    /**
     * Se encarga de ver si la fecha pasada es valida y es mayor del 1/1/1970
     * @param d El dia
     * @param m El mes
     * @param a El anyo
     * @return Devuelve true si la fecha es correcta y es mayor que el 1/1/1970, false en caso contrario
     */
    private boolean isFechaValida(int d, int m, int a)
    {
        if((d < 1 || d > 31) || (m < 1 || m > 12)
                || a < 1970)
            return false;
        //Si se cumple el year es bisiesto
        if(esBisiesto(a))
        {
            //Si el mes es febrero
            if(m == 2)
            {
                if(d <= 29 && d >= 1)
                    return true;
                else
                    return false;
            }
            else
            {
                //Si el mes tiene 31 dias
                if(m%2 == 1 || m ==  8)
                {
                    if(d <= 31 && d >= 1)
                        return true;
                    else
                        return false;
                }
                else
                {
                    if(d <= 30 && d >= 1)
                        return true;
                    else
                        return false;
                }
            }
        }
        else
        {
            //Si el mes es febrero de year normal
            if(m == 2)
            {
                if(d <= 28 && d >= 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                //Mes de 31 dias
                if(m%2 == 1 || m ==  8)
                {
                    if(d <= 31 && d >= 1)
                        return true;
                    else
                        return false;
                }
                else
                {
                    if(d <= 30 && d >= 1)
                        return true;
                    else
                        return false;
                }
            }
        }
    }

    /**
     * Se encarga de calcular si un año es bisiesto o no.
     * @param y The year to be evaluated.
     * @return True si es bisiesto, false en caso contrario.
     */
    private boolean esBisiesto(int y) {
        if(y%4 == 0 || (y%100 == 0 && y %400 == 0))
            return true;
        else
            return false;
    }

    /**
     * Se encarga de ver si una hora pasada es valida o no.
     * @param h La hora.
     * @param m Los minutos.
     * @return Devuelve true si la hora es correcta, false en caso contrario.
     */
    private boolean isHoraValida(int h, int m) {
        if(h <= 23 && h >= 0 && m <= 59 && m >= 0 )
            return true;
        else
            return false;
    }

    /**
     * Adds an amount of days (if the arg is more than 0) to the current date.
     * @param d The amount of days.
     */
    private void sumarDias(int d) {
        if(d > 0)
        {
            int diaSum = d;
            while(diaSum > 0)
            {
                if(esBisiesto(this.year))
                {
                    final int diasFeb = 29;

                    //Es febrero
                    if(this.month == 2)
                    {
                        if((diaSum + this.day) > diasFeb)
                        {
                            diaSum -= (diasFeb-this.day)+1;

                            this.day = 1;
                            this.month++;
                        }
                        else
                        {
                            this.day += diaSum;
                            diaSum = 0;
                        }
                    }
                    else
                    {
                        //Meses de 31 dias
                        if((this.month %2 == 1 && this.month < 8)
                                || (this.month%2 == 0 && this.month >= 8))
                        {
                            final int diasMes = 31;

                            if((diaSum + this.day) > diasMes)
                            {
                                diaSum -= (diasMes-this.day)+1;

                                this.day = 1;
                                if(this.month == 12)
                                {
                                    this.month=1;
                                    this.year++;
                                }
                                else
                                {
                                    this.month++;
                                }
                            }
                            else
                            {
                                this.day += diaSum;
                                diaSum = 0;
                            }
                        }
                        //Meses de 30 dias
                        else
                        {
                            final int diasMes = 30;

                            if((diaSum + this.day) > diasMes)
                            {
                                diaSum -= (diasMes-this.day)+1;

                                this.day = 1;
                                this.month++;
                            }
                            else
                            {
                                this.day += diaSum;
                                diaSum = 0;
                            }
                        }
                    }
                }
                else
                {
                    final int diasFeb = 28;

                    //Es febrero
                    if(this.month == 2)
                    {
                        if((diaSum + this.day) > diasFeb)
                        {
                            diaSum -= (diasFeb-this.day)+1;

                            this.day = 1;
                            this.month++;
                        }
                        else
                        {
                            this.day += diaSum;
                            diaSum = 0;
                        }
                    }
                    else
                    {
                        //Meses de 31 dias
                        if((this.month%2 == 1 && this.month < 8)
                                || (this.month%2 == 0 && this.month >= 8))
                        {
                            final int diasMes = 31;

                            if((diaSum + this.day) > diasMes)
                            {
                                diaSum -= (diasMes-this.day)+1;

                                this.day = 1;
                                if(this.month == 12)
                                {
                                    this.month=1;
                                    this.year++;
                                }
                                else
                                {
                                    this.month++;
                                }
                            }
                            else
                            {
                                this.day += diaSum;
                                diaSum = 0;
                            }
                        }
                        //Meses de 30 dias
                        else
                        {
                            final int diasMes = 30;

                            if((diaSum + this.day) > diasMes)
                            {
                                diaSum -= (diasMes-this.day)+1;

                                this.day = 1;
                                this.month++;
                            }
                            else
                            {
                                this.day += diaSum;
                                diaSum = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Subtracts an amount of days to the current date.
     * @param d The amount of days.
     * @throws TooManyDaysException If the new date will be less than 1970/01/01.
     */
    private void restarDias(int d) throws TooManyDaysException
    {        
        if(d > 0)
        {
        	Time t = new Time(this.day, this.month, this.year, this.hour, this.min);
            int diaSum = d;
            while(diaSum > 0)
            {
                if(esBisiesto(t.year))
                {
                    final int diasFeb = 29;

                    //Es febrero
                    if((t.month-1) == 2)
                    {
                        if((t.day - diaSum) < 1)
                        {
                            diaSum -= t.day;

                            t.day = diasFeb;
                            t.month--;
                        }
                        else
                        {
                            t.day -= diaSum;
                            diaSum = 0;
                        }
                    }
                    else
                    {
                        //Meses de 31 dias
                        if((t.month-1%2 == 1 && t.month-1 < 8)
                                || (t.month-1%2 == 0 && t.month-1 >= 8) || t.month-1 == 1)
                        {
                            final int diasMes = 31;

                            if((t.day - diaSum) < 1)
                            {
                                diaSum -= t.day;

                                t.month--;

                                t.day = diasMes;
                            }
                            else
                            {
                                t.day -= diaSum;
                                diaSum = 0;
                            }
                        }
                        //Meses de 30 dias
                        else
                        {
                            final int diasMes = 30;

                            if((t.day - diaSum) < 1)
                            {
                                diaSum -= t.day;

                                t.day = diasMes;

                              //Si estamos en enero, pasamos a diciembre
                                if(t.month == 1)
                                {
                                    t.month=12;
                                    t.year--;
                                }
                                else
                                {
                                    t.month--;
                                }
                            }
                            else
                            {
                                t.day -= diaSum;
                                diaSum = 0;
                            }
                        }
                    }
                }
                else
                {
                    final int diasFeb = 28;

                    //Es febrero
                    if((t.month-1) == 2)
                    {
                        if((t.day - diaSum) < 1)
                        {
                            diaSum -= t.day;

                            t.day = diasFeb;
                            t.month--;
                        }
                        else
                        {
                            t.day -= diaSum;
                            diaSum = 0;
                        }
                    }
                    else
                    {
                        //Meses de 31 dias
                        if((t.month-1%2 == 1 && t.month-1 < 8)
                                || (t.month-1%2 == 0 && t.month-1 >= 8) || t.month-1 == 1)
                        {
                            final int diasMes = 31;

                            if((t.day - diaSum) < 1)
                            {
                                diaSum -= t.day;

                                t.month--;

                                t.day = diasMes;
                            }
                            else
                            {
                                t.day -= diaSum;
                                diaSum = 0;
                            }
                        }
                        //Meses de 30 dias
                        else
                        {
                            final int diasMes = 30;

                            if((t.day - diaSum) < 1)
                            {
                                diaSum -= t.day;

                                t.day = diasMes;
                                
                              //Si estamos en enero, pasamos a diciembre
                                if(t.month == 1)
                                {
                                    t.month=12;
                                    t.year--;
                                }
                                else
                                {
                                    t.month--;
                                }
                            }
                            else
                            {
                                t.day -= diaSum;
                                diaSum = 0;
                            }
                        }
                    }
                }
            }

            
            if(!isFechaValida(t.day, t.month, t.year))
            { 
               throw new TooManyDaysException();
            }
            else
            {
                this.day = t.day;
                this.month = t.month;
                this.year = t.year;
                this.hour = t.hour;
                this.min = t.min;
            }
        }
    }

    /**
     * Evaluates if a date is more than other
     * @param t The date to be evaluated
     * @return True if t is greater than the current date, false otherwise.
     */
    private boolean isFechaMayor(Time t) {
    	if(this.equals(t))
    		return false;
    	
        if(this.year > t.year)
        	return false;
        else
        {
            if(this.year == t.year)
            {
                if(this.month > t.month)
                	return false;
                else
                {
                    if(this.month == t.month)
                    {
                        if(this.day > t.day)
                        	return false;
                        else
                        {
                            //Si las dos fechas son iguales, comprobamos el mensaje
                            if(this.day == t.day)
                            {
                               if(this.hour > t.hour)
                                   return false;
                                else {
                                   if(this.hour == t.hour) {
                                       if(this.min > t.min)
                                           return false;
                                       else
                                           return true;
                                   }
                                   else
                                       return true;
                               }
                            }
                            else
                            	return true;
                        }
                    }
                    else
                    	return true;
                }
            }
            else
            	return true;
        }
    }

}
