package com.example.demo;

import java.util.Arrays;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class Initializer {

    private final MccGroupRepository mccGroups;
    private final MccRepository codes;

    @Transactional
    @EventListener
    public void init(ApplicationReadyEvent event) {

        if (mccGroups.count() > 0) {
            return;
        }

        MccGroup mccGroup1 = new MccGroup("Group 1");
        MccGroup mccGroup2 = new MccGroup("Group 2");
        mccGroups.save(Arrays.asList(mccGroup1, mccGroup2));

        Mcc mcc1 = new Mcc("0001", "mcc 1");
        Mcc mcc2 = new Mcc("0002", "mcc 2");
        Mcc mcc3 = new Mcc("1500", "mcc 50");
        Mcc mcc4 = new Mcc("1501", "mcc 51");

        codes.save(Arrays.asList(mcc1, mcc2, mcc3, mcc4));

        mccGroup1.addCodes(mcc1, mcc2);
        mccGroup2.addCodes(mcc3, mcc4);
    }
}
