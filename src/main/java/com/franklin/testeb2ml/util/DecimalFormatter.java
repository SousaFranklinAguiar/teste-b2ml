package com.franklin.testeb2ml.util;

import java.text.DecimalFormat;

public class DecimalFormatter {

    public static double decimalFormatter(double num){

        DecimalFormat df = new DecimalFormat("0.00");
        String numString = df.format(num).replace(",", ".");

        return Double.parseDouble(numString);

    }

}
