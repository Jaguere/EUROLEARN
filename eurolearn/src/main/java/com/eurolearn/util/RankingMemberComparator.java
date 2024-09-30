package com.eurolearn.util;

import java.util.Comparator;

public class RankingMemberComparator implements Comparator<RankingMember> {

    @Override
    public int compare(RankingMember rm1, RankingMember rm2) {
       return Double.compare(rm2.getTaxaPresenca(), rm1.getTaxaPresenca());
    }

}
