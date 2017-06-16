package com.study.signupdata.database;

import com.study.signupdata.Member;

import java.util.List;

/**
 * Created by $raina on $5/23/2017.
 */

public interface DatabaseInterface {

    List<Member> showAllMembers();

    void insertMembers (Member member);

}
