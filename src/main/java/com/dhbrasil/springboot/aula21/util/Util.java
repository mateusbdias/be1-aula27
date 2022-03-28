package com.dhbrasil.springboot.aula21.util;

import java.util.Date;
import java.sql.Timestamp;

public class Util {

    public static Timestamp dateToTimestamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

}
