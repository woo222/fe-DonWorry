package com.example.capstone_donworry.fragment.calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.ContextCompat;

import com.example.capstone_donworry.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class CalendarDeco {
    // 날짜 표시
    public static DayViewDecorator dayViewDecorator (Context context) {
        return new DayViewDecorator() {
//            private final Drawable drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector);

            // 모든 날짜를 띄울 때 데코레이션이 필요한지 판단
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return true;
            }

            @Override
            public void decorate(DayViewFacade view) {
//                view.setSelectionDrawable(drawable);
            }
        };
    }

    // 현재 날짜
    public static DayViewDecorator todayViewDecorator (Context context) {
        return new DayViewDecorator() {
            private final Drawable drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector);
            private final CalendarDay today = CalendarDay.today();

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return day.equals(today);
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.setBackgroundDrawable(drawable);
                view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.board_red)));
            }
        };
    }

    // 다른 달의 날짜
    public static DayViewDecorator selectedMonthDecorator (Context context, int selectedMonth) {
        return new DayViewDecorator() {

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return day.getMonth() != selectedMonth;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.text_red)));
            }
        };
    }

    // 일요일 강조
    public static DayViewDecorator sundayDecorator () {
        return new DayViewDecorator() {

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(day.getYear(), day.getMonth() -1, day.getDay());
                return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(Color.BLUE));
            }
        };
    }

    // 토요일 강조
    public static DayViewDecorator saturdayDecorator () {
        return new DayViewDecorator() {

            @Override
            public boolean shouldDecorate(CalendarDay day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(day.getYear(), day.getMonth() -1, day.getDay());
                return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(Color.BLUE));
            }
        };
    }

    // 이벤트가 있는 날짜 표시
    public static DayViewDecorator eventDecorator (Context context, List<AmountItem> amountItemList) {
        return new DayViewDecorator() {
            private final HashSet<CalendarDay> eventDates = new HashSet<>();
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return eventDates.contains(day);
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new DotSpan(10F, ContextCompat.getColor(context, R.color.mid_blue)));
            }
        };
    }
}