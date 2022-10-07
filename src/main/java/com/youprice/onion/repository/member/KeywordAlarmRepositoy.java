package com.youprice.onion.repository.member;


import com.youprice.onion.entity.member.KeywordAlarm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


public interface KeywordAlarmRepositoy extends JpaRepository<KeywordAlarm,Long> {


   //public KeywordAlarm findByKeywordName(String name);



    @Repository
    @RequiredArgsConstructor


    public class KeywordAlarmrepositoy {


        private final EntityManager em;



    }

}
