package com.crio.shorturl;

public class XUrlImpl implements XUrl {

   

    public String registerNewUrl(String longUrl)
    {
        if(!lmap.containsKey(longUrl))
        {
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";
            StringBuilder shortUrl = new StringBuilder();
            
            shortUrl.append("http://short.url/");

            for (int i = 0; i < 9; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            shortUrl.append(AlphaNumericString.charAt(index));
            }
            lmap.put(longUrl,shortUrl.toString());
            smap.put(shortUrl.toString(),longUrl);
            lmapc.put(longUrl,0);
            return shortUrl.toString();
        }
        else{
            return lmap.get(longUrl);
        }
    }
    
    // If shortUrl is already present, return null
    // Else, register the specified shortUrl for the given longUrl
    // Note: You don't need to validate if longUrl is already present, 
    //       assume it is always new i.e. it hasn't been seen before 
    public String registerNewUrl(String longUrl, String shortUrl){
        if(!smap.containsKey(shortUrl))
        {
            lmap.put(longUrl, shortUrl);
            smap.put(shortUrl, longUrl);
            return shortUrl;
        }
        return "null";
    }

    // If shortUrl doesn't have a corresponding longUrl, return null
    // Else, return the corresponding longUrl
    public String getUrl(String shortUrl){
        if(smap.containsKey(shortUrl))
        {
            String t1 = smap.get(shortUrl);
            System.out.println(t1);
           // Integer n = lmapc.get(t1);
            int count = lmapc.containsKey(t1) ? lmapc.get(t1) : 0;
            lmapc.put(t1,++count);
            return t1;
        }
        return "null";
    }

    // Return the number of times the longUrl has been looked up using getUrl()
    public Integer getHitCount(String longUrl){
        if(lmap.containsKey(longUrl))
        {
            return lmapc.get(longUrl);
        }
        return 0;
    }

    // Delete the mapping between this longUrl and its corresponding shortUrl
    // Do not zero the Hit Count for this longUrl
    public void delete(String longUrl){
        String t = lmap.get(longUrl);
        smap.remove(t);
        lmap.remove(longUrl);
    }
}