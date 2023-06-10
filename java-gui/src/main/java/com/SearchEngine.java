package com;

import java.io.*;
import java.net.URL;
import java.util.*;

public class SearchEngine {
    public static final HashMap<String, String> engineDict = new HashMap<String, String>() {{
        put("ahima", "https://ahmia.fi/search/?q=");
        put("torch", "http://torchdeedp3i2jigzjdmfpn5ttjhthh5wbmda2rr3jvqjg5p77c54dqd.onion/search?query=");
        put("Sentor", "http://e27slbec2ykiyo26gfuovaehuzsydffbit5nlxid53kigw3pvz6uosqd.onion/?q=");
        put("OnionLand", "http://3bbad7fauom4d6sgppalyqddsqbf5u5p56b5k5uk2zxsy3d6ey2jobad.onion/search?q=");
    }};

    public static final String[] category = {"drug", "ransomware", "weapon", "hacking", "아이스작대기", "indica", "cannabis", "ectasy"};
}