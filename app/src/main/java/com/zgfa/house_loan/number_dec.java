package com.zgfa.house_loan;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/12/6.
 */
public class number_dec {
    //四舍五入
    public static double getDecimal(double num) {
        if (Double.isNaN(num)) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(num);
        num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return num;
    }
}
