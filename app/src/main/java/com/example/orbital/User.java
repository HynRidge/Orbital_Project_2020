package com.example.closefriendsapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;


@RequiresApi(api = Build.VERSION_CODES.N)
public class User {
    private final int phoneNumber;

    private final String birthday;

    private List<User> friends = new ArrayList<>();

    private PriorityQueue<String> friendsBirthdays = new PriorityQueue<String>(new Comparator<String>() {
        @Override
        public int compare(String b1, String b2) {
           int b1Month = Integer.parseInt("" + b1.charAt(3)+ "" + b1.charAt(4));
           int b1Day = Integer.parseInt("" + b1.charAt(0) + "" + b1.charAt(1));
           int b2Month = Integer.parseInt("" + b2.charAt(3)+ "" + b2.charAt(4));
           int b2Day = Integer.parseInt("" + b1.charAt(0) + "" + b1.charAt(1));
           int res = b1Month - b2Month;
           if(res != 0) {
               return res;
           }
           else {
               return b1Day - b2Day;
           }
        }
    });

    public User(int phoneNumber,String birthday) {

        this.phoneNumber = phoneNumber;

        this.birthday = birthday;
    }

    public void addFriends(User friend) {
        friends.add(friend);
        friendsBirthdays.add(friend.getBirthday());
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public List<User> upcomingBirthdays() {

        List<User> birthdays = new ArrayList<>();

        SimpleDateFormat dateFormat= new SimpleDateFormat("EEEE dd/MM/yyyy");

        Calendar currentCal = Calendar.getInstance();

        String currentdate = dateFormat.format(currentCal.getTime());

        for(int i=0;i<7;i++) {

            currentCal.add(Calendar.DATE, 1);

            String toDate = dateFormat.format(currentCal.getTime());

            for(User friend : friends) {
                String friendBirthday = friend.getBirthday();
                if(toDate.equals(friendBirthday)) {
                    birthdays.add(friend);
                }
            }
        }
        return birthdays;
    }
}
