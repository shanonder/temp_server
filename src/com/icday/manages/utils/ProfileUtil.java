package com.icday.manages.utils;

import java.util.concurrent.atomic.AtomicInteger;

import com.icday.models.ConfigProxy;

public class ProfileUtil {

    private static AtomicInteger counter = new AtomicInteger(0);

    /**
     * 长生消息id
     */
    public static String getAtomicCounter() {
        if (counter.get() > 999999) {
            counter.set(1);
        }
        long time = System.currentTimeMillis();
        int r =  counter.incrementAndGet();
        long returnValue = time * 1000000 + ConfigProxy.getInstance().getMid()%1000000*10000 + r%10000;
        return ""+ returnValue;
    }

	private static long incrementAndGet() {
        return counter.incrementAndGet();
    }

    public static void main(String[] args) {
        System.out.println(ProfileUtil.getAtomicCounter());
    }
    
     
}