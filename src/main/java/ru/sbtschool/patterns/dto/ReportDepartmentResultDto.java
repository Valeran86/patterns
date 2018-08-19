package ru.sbtschool.patterns.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Home on 16.08.2018.
 */
public class ReportDepartmentResultDto {
    private final List<String> columns;
    private final List<Object[]> rows;
    private final List<Object[]> totals;

    public ReportDepartmentResultDto( String... columns ) {
        this.columns = Arrays.asList( columns );
        rows = new LinkedList<>();
        totals = new LinkedList<>();
    }

    public void appendRow( Object[] row ) {
        rows.add( row );
    }

    public void appendTotal( Object[] total ) {
        totals.add( total );
    }

    public String[] getColumns() {
        return columns.toArray( new String[0] );
    }

    public Iterable<? extends Object[]> getRows() {
        return new ArrayList<>( rows );
    }

    public Iterable<? extends Object> getTotals() {
        return new ArrayList<>( totals );
    }
}
