package com.katsman.solanteq;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Katsman
 * @since 29.10.17
 */

public class TestAppender extends AppenderBase<ILoggingEvent> {

    private static final List<ILoggingEvent> events = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent loggingEvent) {
        events.add(loggingEvent);
    }

    static void clear() {
        events.clear();
    }

    static List<ILoggingEvent> getEvents() {
        return new ArrayList<>(events);
    }
}
