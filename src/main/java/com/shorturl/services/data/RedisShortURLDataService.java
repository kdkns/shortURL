package com.shorturl.services.data;

import com.shorturl.config.ConfigSingleton;
import com.shorturl.services.config.ConfigurationEnums;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.*;

import java.net.URI;
import java.time.Duration;
import java.util.List;

public class RedisShortURLDataService implements IShortURLDataServices {

    private static final String SEQUENCE = "sequence_hash";

    protected RedisShortURLDataService(){}


    private final JedisPoolConfig poolConfig = buildPoolConfig();
    private final JedisPool jedisPool = new JedisPool(poolConfig,
                                                      ConfigSingleton.CONFIG.getConfig(ConfigurationEnums.ATTRIBUTES.REDIS_URL.key()),
                                                      Integer.parseInt(ConfigSingleton.CONFIG.getConfig(ConfigurationEnums.ATTRIBUTES.REDIS_PORT.key())) );


    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }


    @Override
    public String getURIAddressFromCode(String urlcode) {
        try (Jedis jedis = jedisPool.getResource()) {
           return jedis.get(urlcode);
        }
    }

    @Override
    public void saveShortUrl(URI uri, String encodedKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(encodedKey, uri.toString());
        }
    }

    @Override
    public void printAllData(Logger logger) {
        logger.debug("In Print All Data");

        try (Jedis jedis = jedisPool.getResource()) {
            ScanParams scanParam = new ScanParams();
            scanParam.match("*"); //TODO Add Prefix Based on Config Parameter
            scanParam.count(50000); // TODO Config Parameter


            ScanResult<String> scanResult = jedis.scan("0", scanParam);
            List<String> keys;
            String nextCursor = "0";
            logger.debug("Next Cursor: " + nextCursor);

            do{
                logger.debug("In Loop");

                nextCursor = scanResult.getStringCursor();
                logger.debug("Next Cursor: " + nextCursor);
                keys = scanResult.getResult();

                for(String key : keys){
                    logger.debug("Data Dumping Keys :" + key + " Value:" + jedis.get(key));
                }

                scanResult = jedis.scan(nextCursor, scanParam);
            }while (!"0".equals(nextCursor));
        }
    }

    @Override
    public long getSalt() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr(SEQUENCE);
        }
    }
}
