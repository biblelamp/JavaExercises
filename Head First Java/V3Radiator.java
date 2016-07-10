import java.util.*;
class V3Radiator extends V2Radiator {
	V3Radiator(ArrayList lglist) {
        super(lglist);
        for(int g=0; g<5; g++) { // fix the problem in using energie
            lglist.add(new SimUnit("V3Radiator"));
        }
    }
}