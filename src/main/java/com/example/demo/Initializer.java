package com.example.demo;

import java.util.Arrays;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MerchantInitializer {

    private final MccGroupRepository mccGroups;
    private final MccRepository codes;

    @Transactional
    @EventListener
    public void init(ApplicationReadyEvent event) {

        if (mccGroups.count() > 0) {
            return;
        }

        MccGroup mccGroup1 = new MccGroup("Agricultural Services");
        MccGroup mccGroup2 = new MccGroup("Contracted Services");
        mccGroups.save(Arrays.asList(mccGroup1, mccGroup2));

        Mcc mcc1 = new Mcc("0001", "TAP(Portugal)");
        Mcc mcc2 = new Mcc("0002", "ANSA International");
        Mcc mcc3 = new Mcc("1500", "Spanair: Sales On Board");
        Mcc mcc4 = new Mcc("1501", "Iberia: Sales On Board");

        codes.save(Arrays.asList(mcc1, mcc2, mcc3, mcc4));

        mccGroup1.addCodes(mcc1, mcc2);
        mccGroup2.addCodes(mcc3, mcc4);
    }
}
