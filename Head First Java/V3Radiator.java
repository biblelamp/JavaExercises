import java.util.*;
class V3Radiator extends V2Radiator {
	V3Radiator(ArrayList lglist) {
        super(lglist);
        for(int g=0; g<10; g++) {
            lglist.add(new SimUnit("V3Radiator"));
        }
    }
}